# PennyWise TODO

## Phase 1: Core Website and Backend

### Project setup
- [x] Confirm the website structure and frontend pages.
- [x] Keep the Spring Boot backend separate from frontend presentation code.
- [ ] Organize backend packages into `entity`, `repository`, `service`, `controller`, `dto`, `config`, `exception`, and `security`.
- [ ] Configure environment variables for database credentials and application secrets.
- [ ] Add API documentation with OpenAPI or Swagger.

### Database and users
- [ ] Set up H2 for early local development.
- [ ] Set up PostgreSQL for persistent development and production data.
- [ ] Add Flyway database migrations.
- [ ] Create the `User` entity.
- [ ] Create user registration.
- [ ] Create login and logout.
- [ ] Hash passwords securely.
- [ ] Add password reset.
- [ ] Protect private API endpoints.
- [ ] Ensure each financial record belongs to the correct user.
- [ ] Add user profile, currency, timezone, and language preferences.
- [ ] Add account deletion and data deletion.

## Phase 2: Expense Tracker MVP

### Transactions
- [x] Complete the `Transaction` entity.
- [ ] Add amount, description, category, date, transaction type, payment account, notes, and user ID.
- [x] Create the `TransactionRepository`.
- [x] Create transaction request and response DTOs.
- [x] Create the transaction service.
- [x] Create the transaction controller.
- [ ] Validate that amounts are positive.
- [ ] Validate dates and required fields.
- [ ] Ensure users can access only their own transactions.
- [x] Add create transaction endpoint: `POST /api/transactions`.
- [x] Add list transactions endpoint: `GET /api/transactions`.
- [x] Add transaction details endpoint: `GET /api/transactions/{id}`.
- [ ] Add update transaction endpoint: `PUT /api/transactions/{id}`.
- [ ] Add delete transaction endpoint: `DELETE /api/transactions/{id}`.
- [ ] Add filtering by date range, category, amount, type, and search text.
- [ ] Prevent duplicate transactions where needed.

### Categories and income
- [ ] Create default expense categories.
- [ ] Allow users to create custom categories.
- [ ] Add income transactions.
- [ ] Add recurring transaction support.
- [ ] Add category validation.

### Frontend expense workflow
- [ ] Build the expense entry form.
- [ ] Build the transaction list.
- [ ] Add edit and delete controls.
- [ ] Add loading, empty, and error states.
- [ ] Connect the frontend to the transaction API.
- [ ] Verify that an expense remains after refreshing the website.

## Phase 3: Analytics and Charts

- [ ] Calculate total spending for the current month.
- [ ] Calculate total income.
- [ ] Calculate net income.
- [ ] Calculate spending by category.
- [ ] Calculate spending over time.
- [ ] Find largest expenses.
- [ ] Compare spending with previous months.
- [ ] Add monthly analytics endpoint: `GET /api/analytics/monthly`.
- [ ] Add category analytics endpoint: `GET /api/analytics/categories`.
- [ ] Add spending trend endpoint: `GET /api/analytics/trends`.
- [ ] Add expense pie chart to the website.
- [ ] Add monthly spending chart.
- [ ] Add income versus expense chart.
- [ ] Add date range controls for charts.

## Phase 4: Budgets and Financial Goals

### Budgets
- [ ] Create a `Budget` entity.
- [ ] Allow users to set monthly budgets.
- [ ] Add category-specific budgets.
- [ ] Calculate budget usage.
- [ ] Show warnings when spending approaches a budget.
- [ ] Show warnings when a budget is exceeded.

### Financial goals
- [ ] Create a `FinancialGoal` entity.
- [ ] Add goal name, target amount, current amount, target date, type, and user ID.
- [ ] Create goal endpoints.
- [ ] Allow users to create and edit goals.
- [ ] Allow users to record contributions.
- [ ] Calculate goal progress.
- [ ] Estimate whether the user is on schedule.
- [ ] Add emergency fund goals.
- [ ] Add savings goals.
- [ ] Add debt payoff goals.
- [ ] Build the goals page.
- [ ] Display progress over time.

## Phase 5: Financial Responsibility Score

- [ ] Define transparent scoring rules.
- [ ] Consider savings consistency.
- [ ] Consider spending compared with income.
- [ ] Consider progress toward financial goals.
- [ ] Consider overdrafts and missed payments if reliable data is available.
- [ ] Consider emergency fund progress.
- [ ] Avoid using protected characteristics.
- [ ] Calculate a score between 0 and 100.
- [ ] Return the reasons behind the score.
- [ ] Return strengths and improvement suggestions.
- [ ] Add score history over time.
- [ ] Build the score page and explanation UI.

## Phase 6: Education, Scams, and FAQs

### Financial education
- [ ] Create educational content for credit scores.
- [ ] Add budgeting lessons.
- [ ] Add saving lessons.
- [ ] Add investing basics.
- [ ] Add emergency fund education.
- [ ] Add debt education.
- [ ] Add tax education.
- [ ] Add retirement account education.
- [ ] Explain compound interest.
- [ ] Explain banking safety.
- [ ] Add article categories and difficulty levels.
- [ ] Add education progress tracking.
- [ ] Add quizzes later.

### Scam prevention
- [ ] Create a scam-prevention section.
- [ ] Explain phishing scams.
- [ ] Explain fake investment opportunities.
- [ ] Explain romance scams.
- [ ] Explain fake banking messages.
- [ ] Explain identity theft.
- [ ] Explain job scams.
- [ ] Explain cryptocurrency scams.
- [ ] Explain payment-app scams.
- [ ] List warning signs.
- [ ] Explain what users should do after a scam.
- [ ] Link to official reporting resources.
- [ ] Never request or store sensitive bank passwords.

### FAQs
- [ ] Explain how the expense tracker works.
- [ ] Explain bank connections.
- [ ] Explain the responsibility score.
- [ ] Explain whether PennyWise gives financial advice.
- [ ] Explain data protection.
- [ ] Explain account deletion.
- [ ] Explain language support.
- [ ] Add FAQ search later.

## Phase 7: Language Support

- [ ] Support English.
- [ ] Support Spanish.
- [ ] Support Mandarin Chinese.
- [ ] Support French.
- [ ] Support Japanese.
- [ ] Support Korean.
- [ ] Support Urdu.
- [ ] Support Hindi.
- [ ] Support Vietnamese.
- [ ] Support Tagalog.
- [ ] Translate navigation and buttons.
- [ ] Translate education content.
- [ ] Translate scam warnings.
- [ ] Translate validation and error messages.
- [ ] Store each user's language preference.
- [ ] Format dates, numbers, and currency correctly.
- [ ] Add right-to-left layout support for Urdu.
- [ ] Review translations for financial accuracy.

## Phase 8: Bank Integration

- [ ] Research PNC API access and eligibility.
- [ ] Determine whether an approved financial-data provider is needed.
- [ ] Evaluate providers such as Plaid, MX, or Finicity.
- [ ] Never store bank usernames or passwords.
- [ ] Store access tokens encrypted.
- [ ] Record user consent.
- [ ] Create connected account records.
- [ ] Build secure account connection flow.
- [ ] Sync account balances.
- [ ] Sync transactions.
- [ ] Prevent duplicate imported transactions.
- [ ] Add sync status and error handling.
- [ ] Allow users to disconnect accounts.
- [ ] Add clear bank-data privacy information.
- [ ] Test bank integration with sandbox accounts before production.

## Phase 9: Roth IRA Calculator

- [ ] Create an educational Roth IRA calculator.
- [ ] Accept current age.
- [ ] Accept retirement age.
- [ ] Accept current savings.
- [ ] Accept monthly contribution.
- [ ] Accept expected annual return.
- [ ] Accept inflation estimate.
- [ ] Calculate projected balances.
- [ ] Show low, medium, and high scenarios.
- [ ] Display all assumptions.
- [ ] Explain that projections are not guarantees.
- [ ] Add clear financial disclaimer.
- [ ] Add educational information about Roth IRAs.

## Phase 10: Investment Education and Guidance

- [ ] Define the difference between education and financial advice.
- [ ] Research legal and regulatory requirements before personalized recommendations.
- [ ] Allow users to enter income, expenses, debt, savings, goals, risk tolerance, and time horizon only if appropriate.
- [ ] Provide educational suggestions instead of guaranteed recommendations.
- [ ] Explain emergency funds.
- [ ] Explain high-interest debt.
- [ ] Explain diversification.
- [ ] Explain risk and time horizon.
- [ ] Encourage users to consult a qualified professional.
- [ ] Review all investment language for misleading claims.

## Phase 11: Optional Investment Game

- [ ] Decide whether the game belongs in the first release.
- [ ] Create four playable characters.
- [ ] Give each character a different career.
- [ ] Add income and expense scenarios.
- [ ] Add life events.
- [ ] Add simulated investments.
- [ ] Add rewards earned through gameplay.
- [ ] Add market crash events.
- [ ] Explain the educational lesson behind each event.
- [ ] Keep game money separate from real financial data.
- [ ] Make it clear that the game is not financial advice.
- [ ] Test game balance and accessibility.

## Phase 12: Optional Chrome Extension

- [ ] Decide whether a Chrome extension is needed.
- [ ] Track purchases only with clear user permission.
- [ ] Allow users to save a purchase as an expense.
- [ ] Show budget warnings before checkout where appropriate.
- [ ] Open the PennyWise dashboard.
- [ ] Use secure authentication between the extension and website.
- [ ] Request the minimum browser permissions.
- [ ] Write a clear extension privacy policy.
- [ ] Test extension behavior across supported browsers.

## Security and Privacy

- [ ] Hash passwords; never store plain-text passwords.
- [ ] Use HTTPS in deployed environments.
- [ ] Validate and sanitize all API input.
- [ ] Prevent users from accessing other users' financial records.
- [ ] Protect against SQL injection through parameterized database access.
- [ ] Protect against cross-site scripting and cross-site request forgery.
- [ ] Encrypt sensitive tokens and secrets.
- [ ] Do not log bank credentials or access tokens.
- [ ] Add rate limiting to login and sensitive endpoints.
- [ ] Add audit logging for important account actions.
- [ ] Create a privacy policy.
- [ ] Create terms of service.
- [ ] Add data export and account deletion.
- [ ] Define how long financial data is retained.
- [ ] Review financial, privacy, and investment compliance requirements.

## Testing and Deployment

- [ ] Add unit tests for services.
- [ ] Add repository tests.
- [ ] Add controller/API tests.
- [ ] Test invalid amounts and dates.
- [ ] Test authentication and authorization.
- [ ] Test that users cannot access another user's data.
- [ ] Test analytics calculations.
- [ ] Test goal calculations.
- [ ] Test language switching.
- [ ] Test responsive layouts.
- [ ] Test accessibility with keyboard navigation and screen readers.
- [ ] Add continuous integration.
- [ ] Configure production database backups.
- [ ] Add application monitoring and health checks.
- [ ] Deploy the backend securely.
- [ ] Deploy the website.
- [ ] Configure a custom domain.
- [ ] Monitor errors and performance.

## First Milestone

- [ ] User can register and log in.
- [ ] User can add one expense.
- [ ] Expense is saved in the database.
- [ ] User can view saved expenses.
- [ ] User can edit and delete an expense.
- [ ] User can see monthly totals by category.
- [ ] Website displays a basic spending chart.
- [ ] Tests verify the complete expense workflow.

## Recommended Build Order

1. Expense tracking foundation.
2. Database persistence and user accounts.
3. Analytics and charts.
4. Budgets and financial goals.
5. Financial responsibility score.
6. Education, scams, and FAQs.
7. Language support.
8. Bank integration.
9. Roth IRA calculator.
10. Investment education and guidance.
11. Chrome extension.
12. Investment game.
