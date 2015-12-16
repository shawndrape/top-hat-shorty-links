import top_hat_demo.ShortenedURI

class BootStrap {

    def init = { servletContext ->
        new ShortenedURI(orig_url: "https://google.com", public_hash: "aJ8Bo").save()
        new ShortenedURI(orig_url: "https://tophat.com", public_hash: "8jR56").save()
    }
    def destroy = {
    }
}
