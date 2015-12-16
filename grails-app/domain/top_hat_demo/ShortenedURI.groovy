package top_hat_demo


class ShortenedURI {
    String public_hash
    String orig_url

    static constraints = {
        orig_url blank: false, url: true
        public_hash blank: true, size:4..20, unique: true
    }
}