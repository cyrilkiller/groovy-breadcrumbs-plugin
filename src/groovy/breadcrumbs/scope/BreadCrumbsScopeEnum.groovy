package breadcrumbs.scope

/**
 * Define diffrent scope for redefine breadcrumbs
 * @author cyril
 *
 */
enum BreadCrumbsScopeEnum {

	SESSION(1),
	REQUEST(2),
	STATIC(3)
	
	private final int value

	BreadCrumbsScopeEnum(Integer value) { this.value = value	}
	
	public int value() { return value }
}
