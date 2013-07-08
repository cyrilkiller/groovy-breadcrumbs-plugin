package breadcrumbs.exception

import org.springframework.validation.Errors


class BreadCrumbsException extends RuntimeException {

	public Errors errorList
	
	BreadCrumbsException(){
		super()
	}
	
    public BreadCrumbsException(String message) {
        super(message);
    }
   
    public BreadCrumbsException(Throwable cause) {
        super(cause);
    }
    
    public BreadCrumbsException(String message, Throwable cause) {
        super(message, cause);
    }

    
    public BreadCrumbsException(Errors errors){
        errorList = errors;
    }
}
