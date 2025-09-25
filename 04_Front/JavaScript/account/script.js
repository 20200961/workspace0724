let accounts = JSON.parse(localStorage.getItem('accounts')) || [];
let filterState = 'all';

const accountList = document.getElementById('account-list');
const productInput = document.getElementById('product');
const priceInput = document.getElementById('price');
const filterBtns = document.querySelectorAll('.list-buttons button');
const btnIncome = document.querySelector('.btn-income');
const btnExpense = document.querySelector('.btn-expense');

let currentType = true;

btnIncome.addEventListener('click', function() {
    currentType = true; 
    btnIncome.classList.add('active');
    btnExpense.classList.remove('active');
});

btnExpense.addEventListener('click', function() {
    currentType = false; 
    btnExpense.classList.add('active');
    btnIncome.classList.remove('active');
});

function init(){
    bindEvents();
    render();
}

function bindEvents(){
    const addbtn = document.getElementById('list-add-btn');
    addbtn.addEventListener('click',addAccount);

    filterBtns.forEach(function(btn){
        btn.addEventListener('click',function(ev){
            setFilter(ev.target.dataset.filter);
        })
    })
}

function addAccount() {
    const description = productInput.value.trim();
    const amount = Number(priceInput.value);

    if (!description || !amount) return;

    const account = {
        id: Date.now(),
        description: description,
        amount: amount,
        type: currentType,
        date: new Date().toLocaleDateString(),
        completed: false
    };

    accounts.push(account);

    productInput.value = "";
    priceInput.value = "";

    saveAccounts();
    render();
}

function deleteAccount(id){
    const newAccount = [];
    for (let account of accounts){
        if(account.id === id)
            continue;

        newAccount.push(account);
    }
    accounts = newAccount;
    saveAccounts();
    render();
}

function getFilteredAccounts(){
    const filteredAccounts = [];
    if(filterState === 'income'){
        for(let account of accounts){
            if(account.type == true){
                filteredAccounts.push(account);
            }
        }
    } else if(filterState === 'expense'){
        for(let account of accounts){
            if(account.type === false){
                filteredAccounts.push(account);
            }
        }
    } else{
        return accounts;
    }

    return filteredAccounts;
}

function saveAccounts(){
    localStorage.setItem('accounts',JSON.stringify(accounts));
}

function render(){
    
    accountList.innerHTML = "";

    const filteredAccounts = getFilteredAccounts();

    if(filteredAccounts.length === 0){
        emptyStateRender();
    } else{
        filteredAccounts.forEach(function (account){
            accountItemRender(account);
        })
    }

    updateCount();
    updateClearButton();
}

function emptyStateRender(){
    const emptyEl = document.createElement('div');
    emptyEl.className = 'empty-state';
    emptyEl.innerHTML = '가계부가 없습니다';
    accountList.appendChild(emptyEl);
}

function accountItemRender(account){
    const accountItem = document.createElement('li');
    const sign = account.type ? '+' : '-';
    const amountClass = account.type ? 'income-amount' : 'expense-amount';
    accountItem.className = 'account-item' + (account.completed ? 'completed' : '');

    accountItem.innerHTML = `<div class="account-info">
            <span class="date">${account.date}</span>
            <span class="description">${account.description}</span>
            </div>
            <span class="${amountClass}">${sign}${account.amount.toLocaleString()}원</span>
            <button class="delete-btn">삭제</button>`;

    const deleteBtn = accountItem.querySelector('.delete-btn');

    if (deleteBtn) {
        deleteBtn.addEventListener('click', function() {
            deleteAccount(account.id);
        });
    }

    accountList.appendChild(accountItem);

}

function setFilter(filter){
    filterState = filter;

    filterBtns.forEach(function(btn){
        btn.className = (btn.dataset.filter === filter ? "active" : "");
    })

    render();
}

function updateCount() {
    const filteredAccounts = getFilteredAccounts();
    
    let totalIncome = 0;
    let totalExpense = 0;

    for (let account of accounts) {
        if (account.type) { 
            totalIncome += account.amount;
        } else {           
            totalExpense += account.amount;
        }
    }

    const balance = totalIncome - totalExpense;

    document.getElementById('add-text').textContent = totalIncome.toLocaleString() + "원";
    document.getElementById('lost-text').textContent = totalExpense.toLocaleString() + "원";
    document.getElementById('money').textContent = balance.toLocaleString() + "원";
    document.getElementById('money-text').textContent = balance.toLocaleString() + "원";
}




document.addEventListener('DOMContentLoaded', init);