# file-utils  
##### 一个前端文件下载、预览、打印工具  
## 0.功能实现说明  
  模拟iframe form提交的方式实现post方式下载文件   
## 1.引入  
    <script src="fileUtils.js"></script>
    <script>
        var fileUtil = new fileUtils();
    </script>
## 2.方法示例  
    <script>
        /**
         * 下载文件
         * @param url, 文件路径
         * @param data, obj 传递参数，与 jquery ajax内的参数 data一致，可以传数组
         */
        fileUtil.download(url,data);//下载
        
        /**
         * 打印文件，能预览的才能打印，一般只有 html、text、image、pdf等文件可以打印
         * @param url, 文件路径
         * @param data, obj 传递参数，与 jquery ajax内的参数 data一致，可以传数组
         */
        fileUtil.print(url,data);//打印
        
        /**
         * 预览
         * @param url,  文件路径
         * @param data, obj 传递的参数，与 jquery ajax内的参数 'data' 一致，可以传数组
         * @param iframe, 必填，一个创建好的iframe的dom对象
         */
        fileUtil.preview(url, data, iframe);//预览
    </script>
