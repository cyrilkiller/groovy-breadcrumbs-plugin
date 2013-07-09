package breadcrumbs.exception

import org.springframework.validation.Errors

class BreadCrumbsException extends RuntimeException {

	Errors errorList

	BreadCrumbsException(){
		super()
	}

	BreadCrumbsException(String message) {
		super(message)
	}

	BreadCrumbsException(Throwable cause) {
		super(cause)
	}

	BreadCrumbsException(String message, Throwable cause) {
		super(message, cause)
	}

	BreadCrumbsException(Errors errors){
		errorList = errors
	}
}
