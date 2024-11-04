package haj.com.entity.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum IdPassportEnum.
 */
public enum BugReportType {

	Support("System Support") {
	},

	Issue("Issue/Bug") {
	},

	ExternalParty("External User Support") {

	};

	/** The display name. */
	private String displayName;

	/**
	 * Instantiates a new id passport enum.
	 *
	 * @param displayNameX
	 *            the display name X
	 */
	private BugReportType(String displayNameX) {
		displayName = displayNameX;
	}

	/**
	 * Gets the friendly name.
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return displayName;
	}

	/**
	 * Gets the id passport enum by value.
	 *
	 * @param value
	 *            the value
	 * @return the id passport enum by value
	 */
	public static final BugReportType getIdPassportEnumByValue(int value) {
		for (BugReportType status : BugReportType.values()) {
			if (status.ordinal() == value) return status;
		}

		return null;
	}
}
