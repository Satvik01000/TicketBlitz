# TicketBlitz

A high-concurrency ticket booking system designed to handle flash sales without data inconsistencies. Built to prevent "Double Booking" race conditions under heavy load.

## 🚀 Key Features

* **Concurrency Control:** Uses Redis Distributed Locks (Redisson) to ensure only 1 user can book a specific seat at a time.
* **High Performance:** Optimized to handle **1,600+ transactions per second** on a single node.
* **Fail-Fast Architecture:** Rejects conflicting requests in **<10ms** by checking locks in memory before touching the database.
* **Scalable:** Containerized with Docker and deployed on Google Cloud Run.

## 🛠️ Tech Stack

* **Language:** Java 21, Spring Boot 3
* **Database:** PostgreSQL 15
* **Caching/Locking:** Redis (Redisson)
* **DevOps:** Docker, Google Cloud Run
* **Testing:** Apache JMeter

## 📊 Benchmark Results

Validated using Apache JMeter with **1,000 concurrent users** targeting a single seat.

| Metric | Result |
| --- | --- |
| **Success Rate** | 0.1% (Exactly 1 winner) |
| **Error Rate** | 99.9% (Exactly 999 rejected) |
| **Throughput** | 1,605 requests/sec |
| **Avg Latency** | <200 ms |

## ⚙️ Setup & Run

**1. Clone the repo:**

```bash
git clone https://github.com/Satvik01000/TicketBlitz.git
cd TicketBlitz

```

**2. Run with Docker:**

```bash
docker-compose up --build

```

The API will start at `http://localhost:8080`.

**3. Run Load Test (CLI):**
Prerequisite: Install [Apache JMeter](https://jmeter.apache.org/).

```bash
jmeter -n -t tests/TicketBlitz.jmx -l results.jtl -e -o ./report

```

## 📂 Project Structure

* `src/main/java`: Source code.
* `JMeter Test`: TicketBlitz.jmx
---

**Author:** [Satvik Mishra](https://github.com/Satvik01000)
