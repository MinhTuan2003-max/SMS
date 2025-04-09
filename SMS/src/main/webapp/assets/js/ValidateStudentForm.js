function validateForm() {
    // Clear previous errors
    clearErrors();

    let isValid = true;

    // Validate Name
    const name = document.getElementById('s-name').value.trim();
    if (name === "") {
        document.getElementById('nameError').innerText = "Please enter the student's name.";
        isValid = false;
    }

    // Validate Semester
    const semester = document.getElementById('s-semester').value;
    if (semester === "") {
        document.getElementById('semesterError').innerText = "Please enter the semester.";
        isValid = false;
    }

    // Validate Courses
    const courses = document.querySelectorAll('input[name="courseIds"]:checked');
    if (courses.length === 0) {
        document.getElementById('courseError').innerText = "Please select at least one course.";
        isValid = false;
    }

    return isValid;
}

function clearErrors() {
    document.getElementById('nameError').innerText = "";
    document.getElementById('semesterError').innerText = "";
    document.getElementById('courseError').innerText = "";
}