

/*
 init : function () {
var _this = this;//함수의 변수화    

$('#btn-save').click(function () {
           
           _this.save();//변수 선언
           
          var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
          };
    });//$('#btn-save').click(function ()END

 save : function () {  	

        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
           
            //url: 'http://localhost:8181/cndsalon/api/v1/posts',
            url: contextPath+'/api/v1/posts',
            
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
            
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = contextPath;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }//save:function()END
};//init:function()END
*/