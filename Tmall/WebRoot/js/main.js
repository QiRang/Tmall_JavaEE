	function checkName(){
			var check=false;
			const pattern = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]/im;
			document.getElementById("name").onchange=function(){
				var name = document.registerForm.name.value;
				console.log(111,name);
				if(pattern.test(name)){
					console.log("name中有特殊字符");
					check=false;
					console.log("false?",check);
					document.getElementById("spanF").style.color="red";
					return check;
				} else {
					check=true;
					console.log("true?",check);
					document.getElementById("spanF").style.color="green";
					return check;
				}
			};
		}
		function checkPassword(){
			var check=false;
			const pattern = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]/im;
			document.getElementById("password").onchange=function(){
				var password = document.registerForm.password.value;
				console.log(111,password);
				if(pattern.test(password)){
					console.log("password中有特殊字符");
					check=false;
					console.log("false?",check);
					document.getElementById("spanS").style.color="red";
					return check;
				} else {
					check=true;
					console.log("true?",check);
					document.getElementById("spanS").style.color="green";
					return check;
				}
			};
		}
		function checkRepeatPassword(){
			var check=false;
			document.getElementById("repeatPassword").onchange=function (){
				var password = document.registerForm.password.value;
				var repeatPassword = document.registerForm.repeatPassword.value;
				console.log(111,repeatPassword);
				if(repeatPassword === password){
					console.log("密码一致");
					check=true;
					console.log("true?",check);
					document.getElementById("spanT").style.color="green";
					return check;
				} else {
					check=false;
					console.log("false?",check);
					document.getElementById("spanT").style.color="red";
					return check;
				}
			};
		}
		
		
		
		
		
		
		const pattern = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]/im;
		var checkName;
		var checkPassword;
		var checkRepeatPassword;
		document.getElementById("name").onchange=function (){
			//var check=false;
			var name = document.registerForm.name.value;
			console.log(111,name);
			if(pattern.test(name)){
				console.log("name中有特殊字符");
				checkName=false;
				console.log("false?",checkName);
				document.getElementById("spanF").style.color="red";
				return checkName;
			} else {
				checkName=true;
				console.log("true?",checkName);
				document.getElementById("spanF").style.color="green";
				return checkName;
			}
		};
		document.getElementById("password").onchange=function (){
			//var check=false;
			var password = document.registerForm.password.value;
			console.log(111,password);
			if(pattern.test(password)){
				console.log("password中有特殊字符");
				checkPassword=false;
				console.log("false?",checkPassword);
				document.getElementById("spanS").style.color="red";
				return checkPassword;
			} else {
				checkPassword=true;
				console.log("true?",checkPassword);
				document.getElementById("spanS").style.color="green";
				return checkPassword;
			}
		};
		document.getElementById("repeatPassword").onchange=function (){
			//var check=false;
			var password = document.registerForm.password.value;
			var repeatPassword = document.registerForm.repeatPassword.value;
			console.log(111,repeatPassword);
			if(repeatPassword === password){
				console.log("密码一致");
				checkRepeatPassword=true;
				console.log("true?",checkRepeatPassword);
				document.getElementById("spanT").style.color="green";
				return checkRepeatPassword;
			} else {
				checkRepeatPassword=false;
				console.log("false?",checkRepeatPassword);
				document.getElementById("spanT").style.color="red";
				return checkRepeatPassword;
			}
		};
		console.log("name",checkName);
		console.log("password",checkPassword);
		console.log("rps",checkRepeatPassword);
		console.log(222,checkName&&checkPassword&&checkRepeatPassword);
		return checkName&&checkPassword&&checkRepeatPassword;