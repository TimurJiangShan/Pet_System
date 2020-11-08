 $(function(){
        // $("#zhuceli").addClass("active");
        $("#form").on("click","#reg_btn",function(){
          var username = $("#username").val();
          var password = $("#password").val();
          var email = $("#email").val();
          var userType = $("input[name='userType']:checked").val();
          var pattern=/^\w+$/;
          var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
          if (!username) {
            alert('The user name cannot be empty');
            return false;
          }
          if(!pattern.test(username)){
        	  alert("ç");
              return false;
          }
          if (username.length < 2) {
              alert('Length of user name should larger than 2');
              return false;
            }
          if (!password) {
            alert('Password can not be empty');
            return false;
          }
          if (password.length < 6) {
              alert('The length of the password cannot be less than 6 bits');
              return false;
            }
          if (password.length > 16) {
              alert('The length of the password cannot be greater than 16 bits');
              return false;
            }
          if (!email) {
            alert('Email cannot be empty');
            return false;
          }
          if(!myreg.test(email)){
        	  alert('The mailbox format is incorrect');
              return false;
          }
            if (!userType) {
                alert('Type cannot be empty');
                return false;
            }
          $.ajax({
              type:"post",
              url:"/register",
              dataType: "json",
              data: {
              username: $("#username").val(),
              password: $("#password").val(),
              email: $("#email").val(), 
              userType: userType
            },
            success:function(data){
            	console.log(data);
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