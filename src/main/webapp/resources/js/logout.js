function logout(){
      layer.confirm('Are you sure you want to log out of root？',{btn:['Log out','Cancel']},function(){
        location.href = "/logout";
      });
    }