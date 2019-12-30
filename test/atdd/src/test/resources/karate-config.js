function fn() {
    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);
    var config = { baseURL: 'http://localhost:8080/samplemicroservice',
        healthPath: '/actuator/health'};
    var env = karate.env || 'local';
    if(env === 'local') {
        config.baseURL = 'http://localhost:8080/samplemicroservice';
    }
    return config;
}