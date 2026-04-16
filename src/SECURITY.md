# Security Documentation — RBAC Lab

## Authentication vs Authorization

**Authentication** is the process of verifying *who you are* — confirming your identity using credentials like a username and password.

**Authorization** is the process of determining *what you are allowed to do* — checking whether your verified identity has permission to access a specific resource or perform a specific action.

---

## Roles Used

| Role | Description |
|------|-------------|
| `USER` | Standard user; can access `/secure/hello` |
| `ADMIN` | Administrator; can access both endpoints |

---

## Protected URLs

| URL | Allowed Roles |
|-----|---------------|
| `/secure/hello` | `USER`, `ADMIN` |
| `/secure/admin` | `ADMIN` only |

---

## How to Test

### Test users created in Payara file realm

| Username | Group/Role |
|----------|------------|
| `user1`  | `USER`     |
| `admin1` | `ADMIN`    |

### Test URLs (adjust context root to match your deployment)

Assuming context root is `/Lab04-1.0-SNAPSHOT` (check your `pom.xml` or deployment settings):

| Test | URL | Expected Result |
|------|-----|-----------------|
| user1 → hello | `http://localhost:8080/Lab04-1.0-SNAPSHOT/secure/hello` | ✅ Allowed |
| user1 → admin | `http://localhost:8080/Lab04-1.0-SNAPSHOT/secure/admin` | ❌ 403 Forbidden |
| admin1 → admin | `http://localhost:8080/Lab04-1.0-SNAPSHOT/secure/admin` | ✅ Allowed |

> Always use a private/incognito browser window when switching users — BASIC auth caches credentials in the browser session.

---

## Security Configuration Summary

- **Auth method:** BASIC (browser prompts for credentials)
- **Realm:** `file` (users stored in Payara's built-in file realm)
- **Role mapping:** Defined in `glassfish-web.xml` — Payara group names map 1:1 to `web.xml` role names
- **Constraints:** Declared in `web.xml` using `<security-constraint>` blocks