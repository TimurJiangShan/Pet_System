$(function(){
          // $("#loginli").addClass("active");
          $("#form").on("click","#btn",function(){
            var username = $("#username").val();
            var password = $("#password").val();
            if (username.length === 0) {
              alert('Username can not be empty');
              return false;
            }
            if (password.length === 0) {
              alert('Password can not be empty');
              return false;
            }
            $.ajax({
              type:"post",
              url:"/login",
              dataType: "json",
              data: {
                username: $("#username").val(),
                password: $("#password").val(),
              },
              success:function(data){
                if(data.success != null && data.success == false){
                  alert(data.error);
                  return false;
                }else{
                  location.href = "/";
                }
              },
              error:function(data){

              }
            });
          });
        });