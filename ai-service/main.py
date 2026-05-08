from fastapi import FastAPI, UploadFile, File
import pdfplumber
import spacy

app = FastAPI()

nlp = spacy.load("en_core_web_sm")

KNOWN_SKILLS = [
    "Java",
    "Spring",
    "Spring Boot",
    "Angular",
    "React",
    "Docker",
    "Kubernetes",
    "PostgreSQL",
    "MongoDB",
    "Kafka",
    "Python",
    "FastAPI",
    "Microservices",
    "AWS",
    "Git",
    "CI/CD",
    "JWT",
    "OAuth2",
    "Hibernate",
    "SQL",
    "Linux"
]


@app.post("/parse-cv")
async def parse_cv(file: UploadFile = File(...)):

    # 📄 extract text from PDF
    text = ""

    with pdfplumber.open(file.file) as pdf:
        for page in pdf.pages:
            text += page.extract_text() or ""

    # 🧠 NLP
    doc = nlp(text)

    # 🎯 skill extraction
    found_skills = []

    for skill in KNOWN_SKILLS:
        if skill.lower() in text.lower():
            found_skills.append(skill)

    # ✅ response
    return {
        "text_length": len(text),
        "skills": found_skills
    }