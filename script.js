// =====================================================
// PENNYWISE JAVASCRIPT
// =====================================================


// =====================================================
// TOOLS / CALCULATORS
// =====================================================

function money(n)
{
    return '$' + Number(n).toLocaleString(undefined, {
        maximumFractionDigits: 2
    });
}

const apiUrl = 'http://localhost:8080/api';

async function postJson(path, body)
{
    const response = await fetch(`${apiUrl}${path}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body)
    });

    if (!response.ok)
    {
        throw new Error(`Backend request failed: ${response.status}`);
    }

    return response.json();
}


// Compound Interest
async function compound()
{
    const result = await postJson('/calculators/compound-interest', {
        startingAmount: +document.getElementById('ci-p').value || 0,
        annualRate: +document.getElementById('ci-r').value || 0,
        years: +document.getElementById('ci-y').value || 0,
        monthlyContribution: +document.getElementById('ci-c').value || 0
    });
    document.getElementById('ci-out').textContent = money(result.result);
}


// Loan Calculator
async function loan()
{
    const result = await postJson('/calculators/loan', {
        loanAmount: +document.getElementById('lo-p').value || 0,
        annualRate: +document.getElementById('lo-r').value || 0,
        years: +document.getElementById('lo-y').value || 0
    });
    document.getElementById('lo-out').textContent = money(result.result);
}


// Tax Calculator
async function tax()
{
    const result = await postJson('/calculators/tax', {
        annualIncome: +document.getElementById('tx-i').value || 0,
        estimatedTaxRate: +document.getElementById('tx-r').value || 0
    });
    document.getElementById('tx-out').textContent = money(result.result);
}


// Investment Calculator
async function invest()
{
    const result = await postJson('/calculators/investment', {
        startingAmount: +document.getElementById('in-p').value || 0,
        monthlyContribution: +document.getElementById('in-c').value || 0,
        annualReturn: +document.getElementById('in-r').value || 0,
        years: +document.getElementById('in-y').value || 0
    });
    document.getElementById('in-out').textContent = money(result.result);
}


// Budget Calculator
async function budget()
{
    const result = await postJson('/calculators/budget', {
        monthlyIncome: +document.getElementById('bu-i').value || 0,
        housing: +document.getElementById('bu-h').value || 0,
        food: +document.getElementById('bu-f').value || 0,
        transportation: +document.getElementById('bu-t').value || 0,
        otherExpenses: +document.getElementById('bu-o').value || 0
    });
    document.getElementById('bu-out').textContent = money(result.result);
}


// =====================================================
// MY MONEY
// =====================================================

async function updateMoney()
{
    const response = await fetch(`${apiUrl}/financial-profile`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            monthlyIncome: +document.getElementById('income').value || 0,
            monthlyExpenses: +document.getElementById('expenses').value || 0,
            totalSavings: +document.getElementById('savings').value || 0,
            totalDebt: +document.getElementById('debt').value || 0,
            creditScore: +document.getElementById('credit').value || 0
        })
    });

    if (!response.ok)
    {
        throw new Error(`Backend request failed: ${response.status}`);
    }

    const profile = await response.json();
    document.getElementById('left').textContent = money(profile.moneyRemaining);
    document.getElementById('rate').textContent = `${Number(profile.savingsRate).toFixed(0)}%`;
    document.getElementById('debtOut').textContent = money(profile.totalDebt);
    document.getElementById('creditOut').textContent = profile.creditScore;
}


async function loadTransactions()
{
    const response = await fetch(`${apiUrl}/transactions`);
    if (!response.ok)
    {
        throw new Error(`Backend request failed: ${response.status}`);
    }
    return response.json();
}

async function createTransaction(expense)
{
    return postJson('/transactions', expense);
}

async function loadFinancialProfile()
{
    const response = await fetch(`${apiUrl}/financial-profile`);
    if (!response.ok)
    {
        return;
    }
    const profile = await response.json();
    document.getElementById('income').value = profile.monthlyIncome;
    document.getElementById('expenses').value = profile.monthlyExpenses;
    document.getElementById('savings').value = profile.totalSavings;
    document.getElementById('debt').value = profile.totalDebt;
    document.getElementById('credit').value = profile.creditScore;
    document.getElementById('left').textContent = money(profile.moneyRemaining);
    document.getElementById('rate').textContent = `${Number(profile.savingsRate).toFixed(0)}%`;
    document.getElementById('debtOut').textContent = money(profile.totalDebt);
    document.getElementById('creditOut').textContent = profile.creditScore;
}

async function loadAnalytics()
{
    const response = await fetch(`${apiUrl}/analytics/monthly`);
    if (!response.ok)
    {
        return;
    }
    const analytics = await response.json();
    const list = document.getElementById('transaction-list');
    if (list)
    {
        const summary = document.createElement('li');
        summary.textContent = `Monthly total: ${money(analytics.totalExpenses)}`;
        list.prepend(summary);
    }
}

async function loadGoals()
{
    const response = await fetch(`${apiUrl}/goals`);
    if (!response.ok)
    {
        return;
    }
    const goals = await response.json();
    const list = document.getElementById('goal-list');
    if (!list)
    {
        return;
    }
    list.replaceChildren();
    goals.forEach((goal) =>
    {
        const item = document.createElement('li');
        item.textContent = `${goal.name}: ${goal.progressPercentage}% (${money(goal.currentAmount)} of ${money(goal.targetAmount)})`;
        list.appendChild(item);
    });
}

document.addEventListener('DOMContentLoaded', () =>
{
    const transactionForm = document.getElementById('transaction-form');
    if (transactionForm)
    {
        transactionForm.addEventListener('submit', async (event) =>
        {
            event.preventDefault();
            const status = document.getElementById('transaction-status');
            try
            {
                await createTransaction({
                    amount: +document.getElementById('transaction-amount').value,
                    description: document.getElementById('transaction-description').value,
                    category: document.getElementById('transaction-category').value,
                    transactionDate: document.getElementById('transaction-date').value
                });
                status.textContent = 'Expense saved.';
                transactionForm.reset();
                const transactions = await loadTransactions();
                const list = document.getElementById('transaction-list');
                list.replaceChildren();
                transactions.forEach((transaction) =>
                {
                    const item = document.createElement('li');
                    item.textContent = `${transaction.transactionDate} - ${money(transaction.amount)} - ${transaction.category}: ${transaction.description}`;
                    list.appendChild(item);
                });
                await loadAnalytics();
            } catch (error)
            {
                status.textContent = 'Could not connect to the backend.';
            }
        });
        loadTransactions().then((transactions) =>
        {
            const list = document.getElementById('transaction-list');
            transactions.forEach((transaction) =>
            {
                const item = document.createElement('li');
                item.textContent = `${transaction.transactionDate} - ${money(transaction.amount)} - ${transaction.category}: ${transaction.description}`;
                list.appendChild(item);
            });
            return loadAnalytics();
        }).catch(() => { });
        loadFinancialProfile().catch(() => { });
    }

    const goalForm = document.getElementById('goal-form');
    if (goalForm)
    {
        document.querySelectorAll('.goal-card .btn-outline').forEach((button) =>
        {
            button.addEventListener('click', (event) =>
            {
                event.preventDefault();
                const card = button.closest('.goal-card');
                const name = card.querySelector('h3').textContent.trim();
                const targetMatch = name.match(/\$([\d,]+)/);
                document.getElementById('goal-name').value = name;
                document.getElementById('goal-target').value = targetMatch
                    ? targetMatch[1].replaceAll(',', '')
                    : '';
                document.getElementById('goal-status').textContent = targetMatch
                    ? 'Goal selected. Add your current amount, then save it.'
                    : 'Goal selected. Add a target amount, then save it.';
                goalForm.scrollIntoView({ behavior: 'smooth', block: 'center' });
            });
        });

        goalForm.addEventListener('submit', async (event) =>
        {
            event.preventDefault();
            try
            {
                await postJson('/goals', {
                    name: document.getElementById('goal-name').value,
                    targetAmount: +document.getElementById('goal-target').value,
                    currentAmount: +document.getElementById('goal-current').value,
                    targetDate: document.getElementById('goal-date').value || null
                });
                document.getElementById('goal-status').textContent = 'Goal saved.';
                goalForm.reset();
                await loadGoals();
            } catch (error)
            {
                document.getElementById('goal-status').textContent = 'Could not connect to the backend.';
            }
        });
        loadGoals().catch(() =>
        {
            document.getElementById('goal-status').textContent =
                'Could not load goals. Make sure Spring Boot is running on port 8080.';
        });
    }
});
// =====================================================
// BEFORE YOU BUY
// =====================================================

let currentPurchase = {
    name: '',
    amount: 0
};

document.getElementById('purchase-form')?.addEventListener('submit', function (event) {
    event.preventDefault();

    const name = document.getElementById('purchase-name').value;
    const amount = +document.getElementById('purchase-amount').value || 0;

    currentPurchase.name = name;
    currentPurchase.amount = amount;

    document.getElementById('purchase-check').style.display = 'block';
    document.getElementById('purchase-status').textContent =
        'Presage check ready. Look at the camera and breathe normally.';
});

function showPurchaseImpact() {

    const income = +document.getElementById('income').value || 0;
    const expenses = +document.getElementById('expenses').value || 0;
    const savings = +document.getElementById('savings').value || 0;
    const credit = +document.getElementById('credit').value || 0;

    const moneyLeft = income - expenses;
    const moneyAfterPurchase = moneyLeft - currentPurchase.amount;

    document.getElementById('purchase-impact').style.display = 'block';

    document.getElementById('purchase-impact-name').textContent =
        currentPurchase.name;

    document.getElementById('purchase-cost').textContent =
        money(currentPurchase.amount);

    document.getElementById('purchase-money-left').textContent =
        money(moneyAfterPurchase);

    document.getElementById('purchase-savings').textContent =
        money(savings);

    document.getElementById('purchase-credit').textContent =
        credit;
}