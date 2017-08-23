package grails3.uploader

import grails.converters.JSON

class ApiController {

    def uploadImage() {
      def file = request.getFile('ionicfile')
  		if(!file.empty) {
  			def ftype
  			if(file.contentType.equals("image/png"))
  				ftype="png"
  			if(file.contentType.equals("image/jpg"))
  				ftype="jpg"
  			if(file.contentType.equals("image/gif"))
  				ftype="gif"
  			if(file.contentType.equals("image/jpeg"))
  				ftype="jpg"
  			def fname = file.getOriginalFilename()+"."+ftype
  			def webRootDir = servletContext.getRealPath("/")
  			def userDir = new File(webRootDir, "/images/")
  			userDir.mkdirs()
  			file.transferTo(new File(userDir, fname))

  			def result = ["message":"success"]
  			withFormat {
  				json {
  					response.status = 200
  					render result as JSON
  				}
  			}
  		}
    }
}
