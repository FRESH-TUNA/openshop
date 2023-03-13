rootProject.name = "tooth"

include("domain")
include("service")
include("common")

include("infrastructure:openauth-mariadb")

include("api:auth-local-jwt-api")
include("api:member-api")
