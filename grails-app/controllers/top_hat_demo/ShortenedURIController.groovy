package top_hat_demo


import grails.rest.*
import grails.converters.*
import org.springframework.format.annotation.DateTimeFormat


class ShortenedURIController extends RestfulController<ShortenedURI> {
    static responseFormats = ['json', 'xml']
    ShortenedURIController() {
        super(ShortenedURI)
    }

    @Override
    protected ShortenedURI createResource(){
        ShortenedURI obj = super.createResource()
        obj.public_hash = quickHash(obj.orig_url)
        return obj
    }

    def getByHash(){
        def pub_hash = params.pub_hash
        def result = ShortenedURI.where{
            public_hash == pub_hash
        }
        if (result.count() > 1)
            throw RuntimeException("Unexpected Duplicate")
        if (result.count() == 0) {
            response.sendError(404)
            return
        }
        redirect(url: result.get().orig_url)
    }

    protected def quickHash(hash){
        def currentDate = new Date().format("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        String unique = hash + currentDate
        String new_hash = unique.hashCode() as String
        return new_hash.substring(0,5)
    }

}
