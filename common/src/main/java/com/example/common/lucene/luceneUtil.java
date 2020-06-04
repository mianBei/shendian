package com.example.common.lucene;

import com.example.common.bean.Constants;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class luceneUtil {
    private Directory directory;

    private IndexReader indexReader;

    private IndexSearcher indexSearcher;

    @Before
    public void setUp() throws IOException {
        // 索引存放的位置，设置在当前目录中
        directory = FSDirectory.open(Paths.get("indexDir/"));
        // 创建索引的读取器
        indexReader = DirectoryReader.open(directory);
        // 创建一个索引的查找器，来检索索引库
        indexSearcher = new IndexSearcher(indexReader);
    }

    @After
    public void tearDown() throws Exception {
        if (indexReader != null) {
            indexReader.close();
        }
    }
    /**
     * 执行查询，并打印查询到的记录数
     *
     * @param query
     * @throws IOException
     */
    public List<HashMap<String,String>> executeQuery(Query query) throws IOException {
        TopDocs topDocs = indexSearcher.search(query, 100);
        // 打印查询到的记录数
        //System.out.println("总共查询到" + topDocs.totalHits + "个文档");
        List<HashMap<String,String>> resultList = new ArrayList<>();
        HashMap<String,String> resultMap = new HashMap<>();
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            // 取得对应的文档对象
            Document document = indexSearcher.doc(scoreDoc.doc);
            resultMap.put("title",document.get("title"));
            resultMap.put("content",document.get("content"));
            resultList.add(resultMap);
        }
        //System.out.println(resultList);
        return resultList;
    }

    /**
     * 分词打印
     *
     * @param analyzer
     * @param text
     * @throws IOException
     */
    public void printAnalyzerDoc(Analyzer analyzer, String text) throws IOException {

        System.out.println("printAnalyzerDoc");
        TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                System.out.println(charTermAttribute.toString());
            }
            tokenStream.end();
        } finally {
            tokenStream.close();
            analyzer.close();
        }
    }

    /**
     * 创建索引文件
     * @param list
     * @return
     */
    public void createLuceneWrite(List<HashMap<String,String>> list) {
        try {
            // 索引存放的位置，设置在当前目录中
            Directory directory = FSDirectory.open(Paths.get("indexDir/"));
            Analyzer analyzer = new IKAnalyzer();// 中文分词
            // 创建索引写入配置
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            // 创建索引写入对象
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
            //往Document中加数据
            for (int i =0;i<list.size();i++){
                // 创建Document对象，存储索引
                Document doc = new Document();
                doc.add(new IntPoint(Constants.ID, i));
                doc.add(new StringField(Constants.TITLE, list.get(i).get("title"), Field.Store.YES));
                doc.add(new TextField(Constants.CONTENT, list.get(i).get("content"), Field.Store.YES));
                doc.add(new StoredField(Constants.ID, i));
                indexWriter.addDocument(doc);
            }
            indexWriter.commit();
            // 关闭流
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文档
     *
     * @throws IOException
     */
    public void deleteDocumentsTest(HashMap<String,String> hm) throws IOException {
        Analyzer analyzer = new IKAnalyzer();// 中文分词
        // 创建索引写入配置
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        // 创建索引写入对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        // 删除title中含有关键词“Spark”的文档
        indexWriter.deleteDocuments(new Term(Constants.TITLE, hm.get("title")));
        // 除此之外IndexWriter还提供了以下方法：
        // DeleteDocuments(Query query):根据Query条件来删除单个或多个Document
        // DeleteDocuments(Query[] queries):根据Query条件来删除单个或多个Document
        // DeleteDocuments(Term term):根据Term来删除单个或多个Document
        // DeleteDocuments(Term[] terms):根据Term来删除单个或多个Document
        // DeleteAll():删除所有的Document
        // 使用IndexWriter进行Document删除操作时，文档并不会立即被删除，而是把这个删除动作缓存起来，当IndexWriter.Commit()或IndexWriter.Close()时，删除操作才会被真正执行。
        indexWriter.commit();
        indexWriter.close();
    }

    /**
     * 测试更新
     * 实际上就是删除后新增一条
     *
     * @throws IOException
     */
    public void updateDocumentTest(HashMap<String,String> hm) throws IOException {
        Analyzer analyzer = new IKAnalyzer();//中文分词
        //创建索引写入配置
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        //创建索引写入对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        Document doc = new Document();
        doc.add(new StringField(Constants.TITLE, hm.get("title"), Field.Store.YES));
        doc.add(new TextField(Constants.CONTENT, hm.get("content"), Field.Store.YES));
        indexWriter.updateDocument(new Term("title", "Spark"), doc);
        indexWriter.close();
    }

    /**
     * 分词查询
     *
     * @throws IOException
     * @throws ParseException
     */
    public List<HashMap<String,String>> queryParserTest(String searchField,String content) throws IOException, ParseException {
        Analyzer analyzer = new IKAnalyzer();//中文分词
        //String searchField = "content";
        //指定搜索字段和分析器
        QueryParser parser = new QueryParser(searchField, analyzer);
        //用户输入内容
        Query query = parser.parse(content);
        //执行查询，并打印查询到的记录数
        return executeQuery(query);
    }

    /**
     * 标题内容检索
     * @throws IOException
     */
    public List<HashMap<String,String>> BooleanQueryTest(String titleText,String contentText) throws IOException {

        String searchField1 = "title";
        String searchField2 = "content";
        Query query1 = new TermQuery(new Term(searchField1, titleText));
        Query query2 = new TermQuery(new Term(searchField2, contentText));
        BooleanQuery.Builder builder = new BooleanQuery.Builder();

        // BooleanClause用于表示布尔查询子句关系的类，
        // 包 括：
        // BooleanClause.Occur.MUST，
        // BooleanClause.Occur.MUST_NOT，
        // BooleanClause.Occur.SHOULD。
        // 必须包含,不能包含,可以包含三种.有以下6种组合：
        //
        // 1．MUST和MUST：取得连个查询子句的交集。
        // 2．MUST和MUST_NOT：表示查询结果中不能包含MUST_NOT所对应得查询子句的检索结果。
        // 3．SHOULD与MUST_NOT：连用时，功能同MUST和MUST_NOT。
        // 4．SHOULD与MUST连用时，结果为MUST子句的检索结果,但是SHOULD可影响排序。
        // 5．SHOULD与SHOULD：表示“或”关系，最终检索结果为所有检索子句的并集。
        // 6．MUST_NOT和MUST_NOT：无意义，检索无结果。

        builder.add(query1, BooleanClause.Occur.SHOULD);
        builder.add(query2, BooleanClause.Occur.SHOULD);
        BooleanQuery query = builder.build();
        // 执行查询，并打印查询到的记录数
        return executeQuery(query);
    }
    /**
     * 高亮处理
     *
     * @throws IOException
     */
    @Test
    public void HighlighterTest() throws IOException, ParseException, InvalidTokenOffsetsException {
        Analyzer analyzer = new IKAnalyzer();//中文分词
        String searchField = "content";
        String text = "几个意思";
        //指定搜索字段和分析器
        QueryParser parser = new QueryParser(searchField, analyzer);
        //用户输入内容
        Query query = parser.parse(text);
        TopDocs topDocs = indexSearcher.search(query, 100);
        // 关键字高亮显示的html标签，需要导入lucene-highlighter-xxx.jar
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span style='color:red'>", "</span>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            //取得对应的文档对象
            Document document = indexSearcher.doc(scoreDoc.doc);
            // 内容增加高亮显示
            TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(document.get("content")));
            String content = highlighter.getBestFragment(tokenStream, document.get("content"));

            System.out.println(content);
        }

    }
}
