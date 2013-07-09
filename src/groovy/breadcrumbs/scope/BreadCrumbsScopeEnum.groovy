package breadcrumbs.scope

/**
 * Define diffrent scope for redefine breadcrumbs
 * @author cyril
 */
enum BreadCrumbsScopeEnum {

	SESSION(1),
	REQUEST(2),
	STATIC(3)

	private final int value

	private BreadCrumbsScopeEnum(Integer value) { this.value = value	}

	int value() { value }
}
