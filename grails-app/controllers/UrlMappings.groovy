class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/links"(resources:'ShortenedURI'){}

//        This will be changed to serve redirects
        "/$pub_hash"(controller: 'shortenedURI', action: 'getByHash')
        "500"(controller: 'InternalServerError')
        "404"(controller: 'NotFound')
    }
}
