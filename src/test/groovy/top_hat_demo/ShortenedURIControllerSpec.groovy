package top_hat_demo

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by shawndrape on 2015-12-16.
 */

@TestFor(ShortenedURIController)
@Mock(ShortenedURI)
class ShortenedURIControllerSpec extends Specification{

    def "create new shortened Uri from domain"(){
        given:
        //TODO - implement domain-based hashing AND database-based hashing
        new ShortenedURI(orig_url: "https://google.com", public_hash: 'xyzr5').save(flush:true)

        when:
        controller.index()

        then:
        response.json.size() == 1
        response.json[0].orig_url == "https://google.com"
    }

    def "create new shortened uri from request"(){
        when:
        controller.request.method = "POST"
        controller.request.contentType = 'application/json'
        controller.request.json = [
                orig_url: "https://tophat.com"
            ]
        controller.save()

        then:
        ShortenedURI.count() == 1
        ShortenedURI.get(1).orig_url == "https://tophat.com"
    }

    def "test that two identical urls produce unique hashes"(){
        when:
        def sample_url1 = "https://tophat.com"
        def sample_url2 = "https://tophat.com"

        def sample_hash1 = controller.quickHash(sample_url1)
        def sample_hash2 = controller.quickHash(sample_url2)

        then:
        sample_hash1 != sample_hash2
        sample_url1 == sample_url2
    }

}
