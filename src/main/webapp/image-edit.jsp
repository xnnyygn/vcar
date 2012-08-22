<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Visual Crop and Resize</title>
    <script src="/vcar/js/jquery.min.js" type="text/javascript"></script>
    <script src="/vcar/js/jquery.jcrop.min.js" type="text/javascript"></script>
    <link media="all" rel="stylesheet" href="/vcar/css/jquery.jcrop.css" type="text/css" />
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/cropAndResizeImage" method="post">
      <fieldset>
        <legend>Visual Crop and Resize</legend>
        <!-- 
        Width <input type="text" id="width" value="100" />
        Height <input type="text" id="height" value="100" />
        <input type="checkbox" id="keepSizeOrRatio" checked="true" />Keep Size/Keep Ratio<br />
        -->
        <img id="pic" src="${pageContext.request.contextPath}/showImage?vcarId=${vcarId}" /><br />
        <input type="hidden" name="vcarId" value="${vcarId}" />
        <input type="hidden" name="left" />
        <input type="hidden" name="top" />
        <input type="hidden" name="width" />
        <input type="hidden" name="height" />
        <button type="submit">Submit</button>
      </fieldset>
    </form>
    <script type="text/javascript">
      $(function(){
        var picJcrop;

        $("#pic").Jcrop({
          onChange: onSelectionChange,
          onSelect: onSelectionChange,
        }, function(){
          picJcrop = this;
        });

        function onSelectionChange(c){
          $("input[name='left']").val(c.x);
          $("input[name='top']").val(parseInt(c.y));
          $("input[name='width']").val(c.w);
          $("input[name='height']").val(c.h);
        }

        /*$("#keepSizeOrRatio").change(function(){
          var width = parseInt($("#width").val());
          var height = parseInt($("#height").val());
          if(this.checked){
            picJcrop.setOptions({allowResize: false});
            picJcrop.setSelect([0, 0, width, height]);
          }else{
            picJcrop.setOptions({aspectRatio: width / height}); 
          }
        });*/
      });
    </script>
  </body>
</html>
