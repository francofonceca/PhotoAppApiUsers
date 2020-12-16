package api.users.ui.model;

import javax.validation.constaints.NotNull;

public class CreateUserRequestModel{
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, "First name must not be less tan two characters")
    private String firstName;
    
    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, "Last name must not be less tan two characters")
    private String lastName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 16, message = "Password must be equal or grater than 8 characters and less than 16 characters")
    private String password;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}