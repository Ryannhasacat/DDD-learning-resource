package contracts.groovy

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description("list all of suppliers.")
	request {
	    method GET()
	    url("/orm/supplier/listOfSuppliers")
	}
	response {
	    body(file('suppliers.json'))
	    status 200
	    headers {
			contentType(applicationJson())
		}
	}
}