# Spring RAG

## Build docker image
```shell
docker build -t my-springboot-app .
```


## Chat with my Assistant
```shell
curl 'http://localhost:8080/assistants/chat' --data-raw '"Hi, my name is Pierre"'
curl 'http://localhost:8080/assistants/chat' --data-raw '"What is the name of my school?"'
```

## Swagger
```shell
http://localhost:8080/swagger-ui/index.html
```

## Sample Documents
```json
{
  "title": "The Lord of the Rings",
  "description": "A fantasy epic written by J.R.R. Tolkien, following the adventures of Frodo Baggins and his companions as they seek to destroy the One Ring.",
  "author": "J.R.R. Tolkien",
  "publisher": "Allen & Unwin",
  "publishedDate": "1954-1955",
  "contentType": "Novel",
  "content": "Frodo and his friends fight Sauron to destroy the One Ring."
}
```

```json
{
  "title": "Harry Potter",
  "description": "A fantasy book series written by J.K. Rowling, following the adventures of Harry Potter, a young wizard, and his friends Hermione Granger and Ron Weasley at Hogwarts School of Witchcraft and Wizardry.",
  "author": "J.K. Rowling",
  "publisher": "Bloomsbury (UK), Scholastic (US)",
  "publishedDate": "1997-2007",
  "contentType": "Novel",
  "content": "Harry Potter battles Lord Voldemort, a dark wizard threatening the wizarding world."
}
```

```json
{
  "title": "1984",
  "description": "A dystopian novel by George Orwell that explores a totalitarian regime under constant surveillance and extreme oppression.",
  "author": "George Orwell",
  "publisher": "Secker & Warburg",
  "publishedDate": "1949",
  "contentType": "Novel",
  "content": "A man rebels against a totalitarian regime led by Big Brother."
}
```

```json
{
  "title": "Pride and Prejudice",
  "description": "A romantic novel by Jane Austen that examines manners, upbringing, and marriage in early 19th-century England.",
  "author": "Jane Austen",
  "publisher": "T. Egerton",
  "publishedDate": "1813",
  "contentType": "Novel",
  "content": "Elizabeth Bennet navigates love and societal expectations."
}
```

```json
{
  "title": "To Kill a Mockingbird",
  "description": "A novel by Harper Lee that deals with serious issues of racial inequality and injustice through the eyes of a child in the American South.",
  "author": "Harper Lee",
  "publisher": "J.B. Lippincott & Co.",
  "publishedDate": "1960",
  "contentType": "Novel",
  "content": "A young girl observes her father's fight against racial injustice."
}
```