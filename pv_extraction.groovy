def dbhostname
def destdb
def dbname(){
    def env_info=["db_name":"","credentials_id":"","user_name":"","password":""]	
    if (params.ENVIRONMENT == 'INT'){
	env_info["db_name"] = "int_database"        
        return env_info
    }else if (params.ENVIRONMENT == 'PPR'){
        env_info["db_name"] = "PPR_database"        
        return env_info
    }else if (params.ENVIRONMENT == 'PRD'){
        env_info["db_name"] = "PRD_database"        
        return env_info
    }
}
def dbdestdb(){
    if (params.ENVIRONMENT == 'INT'){
        destdb = 'kaasmongo'
    }
}


pipeline {
    agent any
    options {
        disableConcurrentBuilds()
        timestamps()
    }

    stages {
        stage("Display the Env variables") {
            steps {
		    script{
		    	echo "Hello World"
		    	def info="${dbname()}"
			for (def key in info.keySet()) {
  				println "key = ${key}, value = ${info[key]}"
			}    
		    	//echo "the DataBase is: ${env_info["db_name"]}"
			// dbname()
			echo "The Destination DB is : ${destdb}"
		    } 
            }
        }
    }
}
