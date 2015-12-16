import grails.rest.render.json.JsonRenderer
import top_hat_demo.ShortenedURI

// Place your Spring DSL code here
beans = {
    linkResolver(JsonRenderer, ShortenedURI){
        excludes = ['class','id']
    }
}
