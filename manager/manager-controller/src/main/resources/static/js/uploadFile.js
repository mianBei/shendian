$uploadFile={
	    bindUploadFile:function(pick_id,callback){
            var uploader = WebUploader.create({
                // swf文件路径
                swf: baseurl+"/js/webuploader/Uploader.swf",
                // 文件接收服务端。
                server: baseurl+"/file/uploadFile.htm",
                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick:{
                    id : '#' + pick_id,
                    multiple : false
                } ,
                // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
                resize: true,
                auto: true,
                duplicate : true
            });
            uploader.on('uploadSuccess', function (file, res) {
                if (callback) {
                    callback(res.status,res.info);
                }
            });
            uploader.on("uploadProgress", function(file, percentage){
               
            });
            uploader.on('uploadError', function (file, res) {
                if (callback) {
                    callback(res.status,res.info);
                }
            });
	    },
	    bindUploadFileByUrl:function(url,pick_id,callback,isResize){
	    	if(isResize==''||isResize==undefined){
	    		isResize=false;	    		
	    	}
            var uploader = WebUploader.create({
                // swf文件路径
                swf: baseurl+"/js/webuploader/Uploader.swf",
                // 文件接收服务端。
                server: url,
                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick:{
                    id : '#' + pick_id,
                    multiple : false
                } ,
                // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
                resize: isResize,
                auto: true,
                duplicate : true
            });
            uploader.on('fileQueued', function (file) {//队列事件
            	index = parent.layer.load(1, {
            	    shade: [0.5,'#CFCFCF'] //0.1透明度的白色背景
            	});
            });
            uploader.on('uploadSuccess', function (file, res) {
            	parent.layer.close(index);
            	if (callback) {
                    callback(res.status,res.info);
                }
            });
            uploader.on("uploadProgress", function(file, percentage){
               
            });
            uploader.on('uploadError', function (file, res) {
            	parent.layer.close(index);
            	if (callback) {
                    callback(res.status,res.info);
                }
            });
	    }

}