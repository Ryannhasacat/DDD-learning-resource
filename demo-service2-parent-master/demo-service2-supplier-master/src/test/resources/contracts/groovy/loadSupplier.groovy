package contracts.groovy

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description("load a supplier by id.")
	request {
	    method GET()
	    url("/orm/supplier/loadSupplier") {
	        queryParameters {
	            parameter("id","20002")
	        }
	    }
	}
	response {
	    body(id:'20002',name:'上海晨光文具股份有限公司(M&G)')
	    status 200
	    headers {
			contentType(applicationJson())
		}
	}
}