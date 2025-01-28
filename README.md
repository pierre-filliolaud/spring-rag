# Spring RAG

## Build docker image
```shell
docker build -t spring-rag .
```

```shell
docker run --rm -p 8080:8080 spring-rag:latest
docker run --rm -p 8080:8080 filliolaud/spring-rag:latest
```

```shell
kubectl cluster-info
kubectl config current-context
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

```json
{
  "title": "Marley & Me",
  "description": "A memoir by John Grogan that tells the heartwarming and humorous story of a family s life with their mischievous Labrador Retriever, Marley.",
  "author": "John Grogan",
  "publisher": "William Morrow",
  "publishedDate": "2005",
  "contentType": "Memoir",
  "content": "The story of a family's bond with their lovable but unruly dog, Marley."
}
```

```json
{
  "title": "A Dog s Purpose",
  "description": "A novel by W. Bruce Cameron that explores the journey of a dog through multiple lives, discovering his purpose through his relationships with various owners.",
  "author": "W. Bruce Cameron",
  "publisher": "Forge Books",
  "publishedDate": "2010",
  "contentType": "Novel",
  "content": "A dog reincarnates to find his purpose in life through his human companions."
}
```

```
give me a fantasy book
give me a book about pets
```

test2