  # 优雅的实现dialog
 
       
        var dialog = defZAlert {
            title = "hello kotlin"
            content = "kt😄"
            okButton("确认") {
                toast("okButton")
            }
            noButton {
                toast("noButton")
            }
            otherButton {
                toast("otherButton")
            }
        }.show()
	
 	dependencies {
     	  implementation 'com.github.zihuan1:view-grace-dialog:latest.integration'
	}
