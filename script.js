// =====================================================
// PENNYWISE JAVASCRIPT
// =====================================================


// =====================================================
// TOOLS / CALCULATORS
// =====================================================

function money(n) {
    return '$' + Number(n).toLocaleString(undefined, {
        maximumFractionDigits: 2
    });
}


// Compound Interest
function compound() {
    const p = +document.getElementById('ci-p').value || 0;
    const r = (+document.getElementById('ci-r').value || 0) / 100;
    const y = +document.getElementById('ci-y').value || 0;
    const c = +document.getElementById('ci-c').value || 0;

    const m = r / 12;
    const n = y * 12;

    const fv = m
        ? p * Math.pow(1 + m, n) + c * ((Math.pow(1 + m, n) - 1) / m)
        : p + c * n;

    document.getElementById('ci-out').textContent = money(fv);
}


// Loan Calculator
function loan() {
    const p = +document.getElementById('lo-p').value || 0;
    const r = (+document.getElementById('lo-r').value || 0) / 100 / 12;
    const n = (+document.getElementById('lo-y').value || 0) * 12;

    const pay = r
        ? p * r * Math.pow(1 + r, n) / (Math.pow(1 + r, n) - 1)
        : (n ? p / n : 0);

    document.getElementById('lo-out').textContent = money(pay);
}


// Tax Calculator
function tax() {
    const i = +document.getElementById('tx-i').value || 0;
    const r = (+document.getElementById('tx-r').value || 0) / 100;

    document.getElementById('tx-out').textContent = money(i * r);
}


// Investment Calculator
function invest() {
    const p = +document.getElementById('in-p').value || 0;
    const c = +document.getElementById('in-c').value || 0;
    const r = (+document.getElementById('in-r').value || 0) / 100 / 12;
    const n = (+document.getElementById('in-y').value || 0) * 12;

    const fv = r
        ? p * Math.pow(1 + r, n) + c * ((Math.pow(1 + r, n) - 1) / r)
        : p + c * n;

    document.getElementById('in-out').textContent = money(fv);
}


// Budget Calculator
function budget() {
    const vals = ['bu-h', 'bu-f', 'bu-t', 'bu-o']
        .reduce((s, id) => s + (+document.getElementById(id).value || 0), 0);

    document.getElementById('bu-out').textContent =
        money((+document.getElementById('bu-i').value || 0) - vals);
}


// =====================================================
// MY MONEY
// =====================================================

function updateMoney() {
    const income = +document.getElementById('income').value || 0;
    const expenses = +document.getElementById('expenses').value || 0;
    const savings = +document.getElementById('savings').value || 0;
    const debt = +document.getElementById('debt').value || 0;
    const credit = +document.getElementById('credit').value || 0;

    document.getElementById('left').textContent =
        money(income - expenses);

    document.getElementById('rate').textContent =
        (income ? ((income - expenses) / income * 100) : 0).toFixed(0) + '%';

    document.getElementById('debtOut').textContent =
        money(debt);

    document.getElementById('creditOut').textContent =
        credit;
}


// =====================================================
// BACKEND TODO - Alllen LockIn
// =====================================================
// Later, the Spring Boot backend will replace the
// calculator calculations above.
//
// The frontend JavaScript will send information to
// Spring Boot using fetch().
//
// Allen/Akansh:
// - Create the calculator API endpoints.
// - Tell us the exact URL for each endpoint.
// - Tell us what data each endpoint expects.
// - Tell us what JSON data each endpoint returns.
//
// IMPORTANT:
// Do not change the HTML IDs without telling the
// frontend me first.
// =====================================================