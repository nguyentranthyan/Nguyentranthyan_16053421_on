package com.example.student.nguyentranthyan_16053421_11;

class Book {
        private int id;
        private String title;
        private String idAuthor;

        public Book() {
            this.id = 0;
            this.title = null;
            this.idAuthor = null;
        }

        public Book(int id, String title, String idAuthor) {
            this.id = id;
            this.title = title;
            this.idAuthor = idAuthor;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIdAuthor() {
            return idAuthor;
        }

        public void setIdAuthor(String idAuthor) {
            this.idAuthor = idAuthor;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", idAuthor=" + idAuthor +
                    '}';
        }
}
