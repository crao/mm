
		function feetToCms(str, id) {
			var res = str.split("-");
			var ft = parseInt(res[0])
			var cm = ft*30.38
			var inc = parseInt(res[1])
			var cmi = inc*2.54
			var cmh = Math.round(cm+cmi)
			document.getElementById(id).value = cmh;
			}
		
		function cmsToFeet(str, id) {
			var cm = parseFloat(str)
			var ft = cm*0.0328084
			var a = Math.trunc(ft)	
			var b = ft - Math.floor(ft)	
			var inc = Math.round(b*11)
			var res = ""+a+"-"+inc
			document.getElementById(id).value = res;
			}
		

		
		function cmsToFt(str) {
			var cm = parseFloat(str)
			var ft = cm*0.0328084
			var a = Math.trunc(ft)	
			var b = ft - Math.floor(ft)	
			var inc = Math.round(b*11)
			var res = ""+a+" ft "+inc + " in"
			return res;
			}
		
		function setValuesInModal(str)
		{ 	
			document.getElementById("mname").innerText = str
		

		}