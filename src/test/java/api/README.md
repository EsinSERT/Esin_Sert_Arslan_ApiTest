# Test Automation – API (Petstore)

Bu projede, **https://petstore.swagger.io/** adresindeki **“pet” endpoint’leri** kullanılarak  
**CRUD (Create, Read, Update, Delete)** operasyonlarını kapsayan **API testleri** yazılmıştır.

Testler **pozitif ve negatif senaryolar** içerecek şekilde hazırlanmıştır.

---

## 🔗 Kullanılan API

- Swagger Petstore: https://petstore.swagger.io/
- Base URL: `https://petstore.swagger.io/v2`
- Endpoint: `/pet`

---

## 🛠 Kullanılan Teknolojiler

- Java 17
- Maven
- Rest Assured
- TestNG

---

## 📂 Proje Yapısı
"
petstore-api-tests
├── src
│   └── test
│       └── java
│           └── api
│               └── PetCrudTest.java
├── pom.xml
└── README.md
"
---

## 🧪 Test Senaryoları

### CREATE – POST /pet
- Pozitif: Geçerli pet bilgisi ile pet oluşturma
- Negatif: Geçersiz / bozuk JSON ile pet oluşturma

### READ – GET /pet/{id}
- Pozitif: Var olan pet bilgisini getirme
- Negatif: Geçersiz ID formatı ile pet getirme

### UPDATE – PUT /pet
- Pozitif: Var olan pet bilgisini güncelleme
- Negatif: Geçersiz / bozuk JSON ile güncelleme

### DELETE – DELETE /pet/{id}
- Pozitif: Var olan pet’i silme
- Negatif: Geçersiz ID formatı ile pet silme

---

## ▶️ Testleri Çalıştırma

### IntelliJ IDEA
- `PetCrudTest` sınıfına sağ tık
- **Run 'PetCrudTest'**

### Maven

``bash
mvn test


## Sonuç

CRUD operasyonları için API testleri başarıyla çalıştırılmıştır.
Pozitif ve negatif senaryolar test edilmiştir.
Proje, Test Automation – API görevi kapsamında hazırlanmıştır.

## Geliştirici

Esin Sert