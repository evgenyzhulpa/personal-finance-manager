const API_BASE_URL = '/api';

const api = {
    // Users API
    async getUsers() {
        const response = await fetch(`${API_BASE_URL}/users`);
        if (!response.ok) throw new Error('Failed to fetch users');
        const data = await response.json();
        return data.users || [];
    },

    async getUser(id) {
        const response = await fetch(`${API_BASE_URL}/users/${id}`);
        if (!response.ok) throw new Error('Failed to fetch user');
        return await response.json();
    },

    async createUser(userData) {
        const response = await fetch(`${API_BASE_URL}/users`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
        });
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || 'Failed to create user');
        }
        return await response.json();
    },

    async updateUser(id, userData) {
        const response = await fetch(`${API_BASE_URL}/users/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
        });
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || 'Failed to update user');
        }
        return await response.json();
    },

    async deleteUser(id) {
        const response = await fetch(`${API_BASE_URL}/users/${id}`, {
            method: 'DELETE',
        });
        if (!response.ok) throw new Error('Failed to delete user');
    },

    // Transactions API
    async getTransactions() {
        const response = await fetch(`${API_BASE_URL}/transactions`);
        if (!response.ok) throw new Error('Failed to fetch transactions');
        const data = await response.json();
        return data.transactions || [];
    },

    async getTransaction(id) {
        const response = await fetch(`${API_BASE_URL}/transactions/${id}`);
        if (!response.ok) throw new Error('Failed to fetch transaction');
        return await response.json();
    },

    async createTransaction(transactionData) {
        const response = await fetch(`${API_BASE_URL}/transactions`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(transactionData),
        });
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || 'Failed to create transaction');
        }
        return await response.json();
    },

    async updateTransaction(id, transactionData) {
        const response = await fetch(`${API_BASE_URL}/transactions/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(transactionData),
        });
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || 'Failed to update transaction');
        }
        return await response.json();
    },

    async deleteTransaction(id) {
        const response = await fetch(`${API_BASE_URL}/transactions/${id}`, {
            method: 'DELETE',
        });
        if (!response.ok) throw new Error('Failed to delete transaction');
    },
};




