package in.co.rays.project_3.model;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * ModelFactory decides which model implementation run
 * 
 * @author Aastik Sahu
 * 
 * 
 *
 */
public final class ModelFactory {

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.project_3.bundle.system");
	private static final String DATABASE = rb.getString("DATABASE");
	private static ModelFactory mFactory = null;
	private static HashMap modelCache = new HashMap();

	private ModelFactory() {

	}

	public static ModelFactory getInstance() {
		if (mFactory == null) {
			mFactory = new ModelFactory();
		}
		return mFactory;
	}
	
	public AccountStatusModelInt getAccountStatusModel() {

		AccountStatusModelInt accountStatusModel = (AccountStatusModelInt) modelCache.get("accountStatusModel");
		if (accountStatusModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				accountStatusModel = new AccountStatusModelHibImpl();
			}
			//in future make changes here
			if ("JDBC".equals(DATABASE)) {
				accountStatusModel = new AccountStatusModelHibImpl();
			}
			modelCache.put("accountStatusModel", accountStatusModel);
		}

		return accountStatusModel;
	}
	
	public BlockModelInt getBlockModel() {

		BlockModelInt blockModel = (BlockModelInt) modelCache.get("blockModel");
		if (blockModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				blockModel = new BlockModelHibImpl();
			}
			//in future make changes here
			if ("JDBC".equals(DATABASE)) {
				blockModel = new BlockModelHibImpl();
			}
			modelCache.put("blockModel", blockModel);
		}

		return blockModel;
	}
	
	public FeatureModelInt getFeatureModel() {

		FeatureModelInt featureModel = (FeatureModelInt) modelCache.get("featureModel");
		if (featureModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				featureModel = new FeatureModelHibImpl();
			}
			//in future make changes here
			if ("JDBC".equals(DATABASE)) {
				featureModel = new FeatureModelHibImpl();
			}
			modelCache.put("featureModel", featureModel);
		}

		return featureModel;
	}
	
	
	public EventRegistrationModelInt getEventRegistrationModel() {

		EventRegistrationModelInt eventRegistrationModel = (EventRegistrationModelInt) modelCache.get("eventRegistrationModel");
		if (eventRegistrationModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				eventRegistrationModel = new EventRegistrationModelHibImpl();
			}
			//in future make changes here
			if ("JDBC".equals(DATABASE)) {
				eventRegistrationModel = new EventRegistrationModelHibImpl();
			}
			modelCache.put("eventRegistrationModel", eventRegistrationModel);
		}

		return eventRegistrationModel;
	}
	
	public VisitorModelInt getVisitorModel() {

		VisitorModelInt visitorModel = (VisitorModelInt) modelCache.get("visitorModel");
		if (visitorModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				visitorModel = new VisitorModelHibImpl();
			}
			//in future make changes here
			if ("JDBC".equals(DATABASE)) {
				visitorModel = new VisitorModelHibImpl();
			}
			modelCache.put("visitorModel", visitorModel);
		}

		return visitorModel;
	}
	
	public PolicyModelInt getPolicyModel() {

		PolicyModelInt policyModel = (PolicyModelInt) modelCache.get("policyModel");
		if (policyModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				policyModel = new PolicyModelHibImpl();
			}
			//in future make changes here
			if ("JDBC".equals(DATABASE)) {
				policyModel = new PolicyModelHibImpl();
			}
			modelCache.put("policyModel", policyModel);
		}

		return policyModel;
	}
	
	public SettingsModelInt getSettingsModel() {

		SettingsModelInt settingsModel = (SettingsModelInt) modelCache.get("settingsModel");
		if (settingsModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				settingsModel = new SettingsModelHibImpl();
			}
			//in future make changes here
			if ("JDBC".equals(DATABASE)) {
				settingsModel = new SettingsModelHibImpl();
			}
			modelCache.put("settingsModel", settingsModel);
		}

		return settingsModel;
	}
	
	public FinanceModelInt getFinanceModel() {

		FinanceModelInt financeModel = (FinanceModelInt) modelCache.get("financeModel");
		if (financeModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				financeModel = new FinanceModelHibImpl();
			}
			//in future make changes here
			if ("JDBC".equals(DATABASE)) {
				financeModel = new FinanceModelHibImpl();
			}
			modelCache.put("financeModel", financeModel);
		}

		return financeModel;
	}
	
	public BannerModelInt getBannerModel() {

		BannerModelInt bannerModel = (BannerModelInt) modelCache.get("bannerModel");
		if (bannerModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				bannerModel = new BannerModelHibImpl();
			}
			//in future make changes here
			if ("JDBC".equals(DATABASE)) {
				bannerModel = new BannerModelHibImpl();
			}
			modelCache.put("bannerModel", bannerModel);
		}

		return bannerModel;
	}
	
	public AuditModelInt getAuditModel() {

		AuditModelInt auditModel = (AuditModelInt) modelCache.get("auditModel");
		if (auditModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				auditModel = new AuditModelHibImpl();
			}
			//in future make changes here
			if ("JDBC".equals(DATABASE)) {
				auditModel = new AuditModelHibImpl();
			}
			modelCache.put("auditModel", auditModel);
		}

		return auditModel;
	}
	
	public AddressModelInt getAddressModel() {

		AddressModelInt addressModel = (AddressModelInt) modelCache.get("addressModel");
		if (addressModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				addressModel = new AddressModelHibImpl();
			}
			//in future make changes here
			if ("JDBC".equals(DATABASE)) {
				addressModel = new AddressModelHibImpl();
			}
			modelCache.put("addressModel", addressModel);
		}

		return addressModel;
	}

	public ProductModelInt getProductModel() {
		ProductModelInt productModel = (ProductModelInt) modelCache.get("productModel");
		if (productModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				productModel = new ProductModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				productModel = new ProductModelHibImp();
			}
			modelCache.put("productModel", productModel);
		}
		return productModel;
	}

	public MarksheetModelInt getMarksheetModel() {
		MarksheetModelInt marksheetModel = (MarksheetModelInt) modelCache.get("marksheetModel");
		if (marksheetModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				marksheetModel = new MarksheetModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				marksheetModel = new MarksheetModelJDBCImpl();
			}
			modelCache.put("marksheetModel", marksheetModel);
		}
		return marksheetModel;
	}

	public CollegeModelInt getCollegeModel() {
		CollegeModelInt collegeModel = (CollegeModelInt) modelCache.get("collegeModel");
		if (collegeModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				collegeModel = new CollegeModelHibImp();

			}
			if ("JDBC".equals(DATABASE)) {
				collegeModel = new CollegeModelJDBCImpl();
			}
			modelCache.put("collegeModel", collegeModel);
		}
		return collegeModel;
	}

	public RoleModelInt getRoleModel() {
		RoleModelInt roleModel = (RoleModelInt) modelCache.get("roleModel");
		if (roleModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				roleModel = new RoleModelHibImp();

			}
			if ("JDBC".equals(DATABASE)) {
				roleModel = new RoleModelJDBCImpl();
			}
			modelCache.put("roleModel", roleModel);
		}
		return roleModel;
	}

	public UserModelInt getUserModel() {

		UserModelInt userModel = (UserModelInt) modelCache.get("userModel");
		if (userModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				userModel = new UserModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				userModel = new UserModelJDBCImpl();
			}
			modelCache.put("userModel", userModel);
		}

		return userModel;
	}
	
	public StaffMemberModelInt getStaffMemberModel() {

		StaffMemberModelInt staffMemberModel = (StaffMemberModelInt) modelCache.get("staffMemberModel");
		if (staffMemberModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				staffMemberModel = new StaffMemberModelHibImpl();
			}
			//in future make changes here
			if ("JDBC".equals(DATABASE)) {
				staffMemberModel = new StaffMemberModelHibImpl();
			}
			modelCache.put("staffMemberModel", staffMemberModel);
		}

		return staffMemberModel;
	}

	public StudentModelInt getStudentModel() {
		StudentModelInt studentModel = (StudentModelInt) modelCache.get("studentModel");
		if (studentModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				studentModel = new StudentModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				studentModel = new StudentModelJDBCImpl();
			}
			modelCache.put("studentModel", studentModel);
		}

		return studentModel;
	}

	public CourseModelInt getCourseModel() {
		CourseModelInt courseModel = (CourseModelInt) modelCache.get("courseModel");
		if (courseModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				courseModel = new CourseModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				courseModel = new CourseModelJDBCImpl();
			}
			modelCache.put("courseModel", courseModel);
		}

		return courseModel;
	}

	public TimetableModelInt getTimetableModel() {

		TimetableModelInt timetableModel = (TimetableModelInt) modelCache.get("timetableModel");

		if (timetableModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				timetableModel = new TimetableModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				timetableModel = new TimetableModelJDBCImpl();
			}
			modelCache.put("timetableModel", timetableModel);
		}

		return timetableModel;
	}

	public SubjectModelInt getSubjectModel() {
		SubjectModelInt subjectModel = (SubjectModelInt) modelCache.get("subjectModel");
		if (subjectModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				subjectModel = new SubjectModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				subjectModel = new SubjectModelJDBCImpl();
			}
			modelCache.put("subjectModel", subjectModel);
		}

		return subjectModel;
	}

	public FacultyModelInt getFacultyModel() {
		FacultyModelInt facultyModel = (FacultyModelInt) modelCache.get("facultyModel");
		if (facultyModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				facultyModel = new FacultyModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				facultyModel = new FacultyModelJDBCImpl();
			}
			modelCache.put("facultyModel", facultyModel);
		}

		return facultyModel;
	}
}
