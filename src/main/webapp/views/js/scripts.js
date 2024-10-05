function editBlog(id, title, content) {
    document.getElementById("titleBlog").value = title;
    CKEDITOR.instances.contentBlog.setData(content); // Set data for CKEditor

    const saveButton = document.getElementById("saveButton");
    const updateButton = document.getElementById("updateButton");
    const deleteButton = document.getElementById("deleteButton");

    saveButton.disabled = true; // Disable Save
    updateButton.disabled = false; // Enable Update
    deleteButton.disabled = false; // Enable Delete

    updateButton.style.backgroundColor = "#4CAF50"; // Green
    deleteButton.style.backgroundColor = "#f44336"; // Red
}

function resetForm() {
    document.querySelector('.form-product').reset();
    CKEDITOR.instances.contentBlog.setData(''); // Reset CKEditor

    const saveButton = document.getElementById("saveButton");
    const updateButton = document.getElementById("updateButton");
    const deleteButton = document.getElementById("deleteButton");

    saveButton.disabled = false; // Enable Save
    updateButton.disabled = true; // Disable Update
    deleteButton.disabled = true; // Disable Delete

    updateButton.style.backgroundColor = "#cccccc"; // Grey
    deleteButton.style.backgroundColor = "#cccccc"; // Grey
}

    function toggleDropdown(event) {
    event.preventDefault();
    const dropdown = event.currentTarget.parentElement;
    dropdown.classList.toggle('active');
}

    function toggleMenu() {
    const navbar = document.querySelector('.navbar');
    navbar.classList.toggle('active');
}