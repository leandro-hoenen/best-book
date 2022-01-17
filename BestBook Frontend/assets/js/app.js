// Book Class: Represents a Book
class Book {
  constructor(picture, title, author, price, isbn) {
    this.picture = picture;
    this.title = title;
    this.author = author;
    this.price = price;
    this.isbn = isbn;
  }
}

// UI Class: Handle UI Tasks
class UI {
  static displayBooks() {
    const books = Store.getBooks();

    books.forEach((book) => UI.addBookToList(book));
  }

  static addBookToList(book) {
    const list = document.querySelector("#book-list");

    const row = document.createElement("tr");

    row.innerHTML = `
      <td>${book.picture}</td>
      <td>${book.title}</td>
      <td>${book.author}</td>
      <td>${book.price}</td>
      <td>${book.isbn}</td>
      <td><a href="#" class="btn btn-danger btn-sm delete">X</a></td>
    `;

    list.appendChild(row);
  }

  static deleteBook(el) {
    if (el.classList.contains("delete")) {
      el.parentElement.parentElement.remove();
    }
  }


  static clearFields() {
  
    document.querySelector("#title").value = "";
    document.querySelector("#author").value = "";
    document.querySelector("#price").value = "";
    document.querySelector("#isbn").value = "";
  }
}

// Store Class: Handles Storage
class Store {
  static getBooks() {
    let books;
    if (localStorage.getItem("books") === null) {
      books = [];
    } else {
      books = JSON.parse(localStorage.getItem("books"));
    }

    return books;
  }

  static addBook(book) {
    const books = Store.getBooks();
    books.push(book);
    localStorage.setItem("books", JSON.stringify(books));
  }

  static removeBook(isbn) {
    const books = Store.getBooks();

    books.forEach((book, index) => {
      if (book.isbn === isbn) {
        books.splice(index, 1);
      }
    });

    localStorage.setItem("books", JSON.stringify(books));
  }
}


// Function Picture

function upload_book() {

  let photo = document.querySelector("#image_input").files[0];
  let formData = new FormData();

    formData.append("photo", photo);
   //fetch('/upload/image', {method: "POST", body: formData});

}

// Event: Display Books
document.addEventListener("DOMContentLoaded", UI.displayBooks);


// Event: Add a Book
document.querySelector("#book-form").addEventListener("submit", (e) => {
  // Prevent actual submit
  e.preventDefault();

  // Get form values
  const title = document.querySelector("#title").value;
  const author = document.querySelector("#author").value;
  const price = document.querySelector("#price").value;
  const isbn = document.querySelector("#isbn").value;

  const photo = document.querySelector("#image_input");
  var uploaded_image = "";

  image_input.addEventListener("change", function() {
    const reader = new FileReader();
    reader.addEventListener("load", () => {
      uploaded_image = reader.result;
      document.querySelector("#display_image").style.backgroundImage = 'url(${uploaded_image})';

    });

    reader.readAsDataURL(this.files[0]);
  });
  

  // Validate
  if (title === "" || author === "" || price === "" || isbn === "") {
    alert("Please fill in all fields");
  } else {
    // Instatiate book
    const book = new Book(photo, title, author, price, isbn);

    // Add Book to UI
    UI.addBookToList(book);

    // Add book to store
    Store.addBook(book);

    // Show success message
    alert("Book Added");

    // Clear fields
    UI.clearFields();
  }
});

// Event: Remove a Book
document.querySelector("#book-list").addEventListener("click", (e) => {
  // Remove book from UI
  UI.deleteBook(e.target);

  // Remove book from store
  Store.removeBook(e.target.parentElement.previousElementSibling.textContent);

  // Show success message
  alert("Book Removed", "success");
});
