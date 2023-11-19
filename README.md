# pf_final_project

# Dating Application - Java GUI Project

## Overview

This Java GUI project implements a simple dating application with multiple frames, event handling, and layout managers. The application allows users to create, update, and delete dating profiles through a user-friendly graphical interface.

## Project Structure

The project consists of several Java classes, each serving a specific purpose:

1. **sign_in_form.java**
    - This class represents the sign-in form where users can create their dating profiles.
    - Features:
        - Input fields for name, age, sex, location, email, and password.
        - Button to create a new profile.
        - Validation for email format and age greater than 0.

2. **homepage.java**
    - The main frame of the application where users can access various functionalities.
    - Features:
        - Display user details.
        - Buttons for starting matching, updating profile, deleting profile, and logging out.

3. **DatingProfile.java**
    - Displays the dating profile based on the user's email.
    - Features:
        - Retrieves user information from the database (if implemented).
        - Displays name, age, and location.
        - Buttons for starting matching and returning to the homepage.

4. **UpdateProfile.java**
    - Form for updating user details.
    - Features:
        - Input fields for updating name, age, sex, location, and password.
        - Button to confirm the update.

5. **Database (Optional)**
    - The project can be extended to include a database (MySQL) for storing user profiles.
    - SQL queries for creating tables and performing CRUD operations.

## Implementation Highlights

1. **Multiple Frames/Windows:**
    - The application features multiple frames, including the main homepage, sign-in form, dating profile display, and an update form.

2. **Event Handling:**
    - Implemented event handling using ActionListeners for button clicks.
    - Events trigger actions such as creating a new profile, updating details, deleting a profile, and more.

3. **Layout Managers:**
    - Utilized layout managers for organizing components in a visually appealing way.
    - `GridLayout`, `FlowLayout`, and `GridBagLayout` are used for different frames.

4. **Functionality:**
    - Users can create dating profiles with relevant information.
    - Profiles can be updated, and users can delete their profiles.
    - Additional functionality includes starting matching and logging out.

## Additional Features (Optional)

- **Database Integration:**
    - The application supports MySQL database integration for storing and retrieving user profiles.
    - SQL queries are included for creating tables and managing user data.

## How to Run

1. Compile the Java files using `javac`.
2. Run the main class (`create_db`) to start the application.

## Assessment Criteria

The project fulfills the specified requirements:

1. **Multiple Frames:** Implemented frames for sign-in, homepage, dating profile, and update profile.
2. **Event Handling:** ActionListeners manage button clicks, triggering relevant actions.
3. **Layout Managers:** Utilized various layout managers for effective component arrangement.
4. **Functionality:**
    - a. Create objects using the sign-in form.
    - b. Reads inserted data, using select method at DatingProfile.
    - c. Update object details using the update form.
    - d. Delete an object via the homepage.


