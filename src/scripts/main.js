class ThogakadeChatbot {
    constructor() {
        this.messagesContainer = document.getElementById('messagesContainer');
        this.messageInput = document.getElementById('messageInput');
        this.sendBtn = document.getElementById('sendBtn');
        this.clearChatBtn = document.getElementById('clearChat');
        this.typingIndicator = document.getElementById('typingIndicator');
        this.quickActionBtns = document.querySelectorAll('.quick-action-btn');
        
        this.initializeEventListeners();
        this.loadChatHistory();
    }

    initializeEventListeners() {
        // Send message on button click
        this.sendBtn.addEventListener('click', () => this.sendMessage());
        
        // Send message on Enter key press
        this.messageInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter' && !e.shiftKey) {
                e.preventDefault();
                this.sendMessage();
            }
        });

        // Clear chat functionality
        this.clearChatBtn.addEventListener('click', () => this.clearChat());

        // Quick action buttons
        this.quickActionBtns.forEach(btn => {
            btn.addEventListener('click', () => {
                const action = btn.getAttribute('data-action');
                this.handleQuickAction(action);
            });
        });

        // Auto-resize input and enable/disable send button
        this.messageInput.addEventListener('input', () => {
            this.sendBtn.disabled = this.messageInput.value.trim() === '';
        });
    }

    async sendMessage() {
        const message = this.messageInput.value.trim();
        if (!message) return;

        // Add user message to chat
        this.addMessage(message, 'user');
        this.messageInput.value = '';
        this.sendBtn.disabled = true;

        // Show typing indicator
        this.showTypingIndicator();

        // Simulate AI response delay
        setTimeout(() => {
            this.hideTypingIndicator();
            const response = this.generateResponse(message);
            this.addMessage(response, 'bot');
        }, 1500 + Math.random() * 1000);

        // Save to localStorage
        this.saveChatHistory();
    }

    addMessage(content, sender) {
        const messageDiv = document.createElement('div');
        messageDiv.className = `message ${sender}-message`;
        
        const time = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
        
        messageDiv.innerHTML = `
            <div class="message-avatar">
                <i class="fas fa-${sender === 'user' ? 'user' : 'robot'}"></i>
            </div>
            <div class="message-content">
                ${this.formatMessage(content)}
            </div>
            <div class="message-time">${time}</div>
        `;

        this.messagesContainer.appendChild(messageDiv);
        this.scrollToBottom();
    }

    formatMessage(content) {
        // Convert line breaks to <br> tags and format lists
        return content
            .replace(/\n/g, '<br>')
            .replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')
            .replace(/\*(.*?)\*/g, '<em>$1</em>');
    }

    generateResponse(message) {
        const lowerMessage = message.toLowerCase();
        
        // Customer-related queries
        if (lowerMessage.includes('customer') || lowerMessage.includes('client')) {
            return this.getCustomerResponse(lowerMessage);
        }
        
        // Item/Inventory related queries
        if (lowerMessage.includes('item') || lowerMessage.includes('inventory') || lowerMessage.includes('stock')) {
            return this.getInventoryResponse(lowerMessage);
        }
        
        // Order related queries
        if (lowerMessage.includes('order') || lowerMessage.includes('purchase')) {
            return this.getOrderResponse(lowerMessage);
        }
        
        // Reports and analytics
        if (lowerMessage.includes('report') || lowerMessage.includes('analytics') || lowerMessage.includes('sales')) {
            return this.getReportsResponse(lowerMessage);
        }
        
        // Database related queries
        if (lowerMessage.includes('database') || lowerMessage.includes('data')) {
            return this.getDatabaseResponse(lowerMessage);
        }
        
        // General help
        if (lowerMessage.includes('help') || lowerMessage.includes('how')) {
            return this.getHelpResponse();
        }
        
        // Greeting responses
        if (lowerMessage.includes('hello') || lowerMessage.includes('hi') || lowerMessage.includes('hey')) {
            return "Hello! Welcome to Thogakade Store Management System. I'm here to help you with customer management, inventory tracking, order processing, and more. What would you like to know?";
        }
        
        // Default response
        return this.getDefaultResponse();
    }

    getCustomerResponse(message) {
        if (message.includes('add') || message.includes('create')) {
            return `To add a new customer in Thogakade:

**Steps:**
1. Open the Customer Form from the dashboard
2. Fill in the required fields:
   • Customer ID (unique identifier)
   • Title (Mr./Mrs./Miss.)
   • Full Name
   • Date of Birth
   • Salary
   • Complete Address
   • City, Province, Postal Code
3. Click the "Add" button
4. The system will confirm successful addition

**Tips:**
• Make sure the Customer ID is unique
• All fields are required for successful registration
• Use the reload button to refresh the customer table`;
        }
        
        if (message.includes('delete') || message.includes('remove')) {
            return `To delete a customer:

**Steps:**
1. Select the customer from the table by clicking on their row
2. The customer details will populate in the form fields
3. Click the "Delete" button
4. Confirm the deletion when prompted

**Important:** 
• Make sure you select the correct customer before deleting
• This action cannot be undone
• Consider if the customer has existing orders before deletion`;
        }
        
        if (message.includes('search') || message.includes('find')) {
            return `To search for customers:

**Current Features:**
• Click on any customer row in the table to view their details
• Use the table to browse all customers
• Customer details auto-populate when selected

**Planned Features:**
• Search by Customer ID
• Filter by city or province
• Advanced search capabilities

The system currently loads all customers in the table for easy browsing.`;
        }
        
        return `**Customer Management in Thogakade:**

**Available Operations:**
• **Add Customer** - Register new customers with complete details
• **View Customers** - Browse all registered customers in a table
• **Delete Customer** - Remove customer records
• **Select Customer** - Click table rows to view details

**Customer Information Stored:**
• Personal details (ID, Name, Title, DOB)
• Contact information (Address, City, Province, Postal Code)
• Financial information (Salary)

**Database Table:** CUSTOMER
**Key Features:** Form validation, table display, selection functionality

Would you like specific help with any customer operation?`;
    }

    getInventoryResponse(message) {
        if (message.includes('add') || message.includes('create')) {
            return `To add new items to inventory:

**Steps:**
1. Open the Item Form from the dashboard
2. Enter item details:
   • **Item Code** - Unique identifier for the item
   • **Description** - Detailed item description
   • **Pack Size** - Package size/unit information
   • **Unit Price** - Price per unit
   • **Quantity on Hand** - Current stock quantity
3. Click "ADD" button
4. System confirms successful addition

**Best Practices:**
• Use clear, descriptive item codes
• Include detailed descriptions for easy identification
• Set accurate unit prices
• Keep quantity updated for stock management`;
        }
        
        if (message.includes('update') || message.includes('edit')) {
            return `To update existing items:

**Steps:**
1. Select an item from the table (click on the row)
2. Item details will populate in the form fields
3. Modify the required information
4. Click "UPDATE" button
5. System confirms successful update

**Updatable Fields:**
• Description
• Pack Size
• Unit Price
• Quantity on Hand

**Note:** Item Code cannot be changed after creation`;
        }
        
        if (message.includes('search')) {
            return `To search for items:

**Current Method:**
1. Enter the Item Code in the Item Code field
2. Click "SEARCH" button
3. If found, item details will display

**Table Features:**
• View all items in the table
• Click any row to select and view details
• Automatic form population on selection

**Search Capabilities:**
• Search by exact Item Code
• Browse all items in table format`;
        }
        
        return `**Inventory Management in Thogakade:**

**Available Operations:**
• **Add Item** - Register new products
• **Update Item** - Modify existing item details
• **Delete Item** - Remove items from inventory
• **Search Item** - Find items by code
• **View All Items** - Browse complete inventory

**Item Information:**
• Item Code (Primary Key)
• Description
• Package Size
• Unit Price
• Quantity on Hand

**Database Table:** item
**Key Features:** CRUD operations, stock tracking, search functionality

The system also supports automatic stock updates during order processing.`;
    }

    getOrderResponse(message) {
        return `**Order Management System (In Development):**

**Planned Features:**
• **Place Orders** - Create new customer orders
• **Order Details** - Manage order line items
• **Order Tracking** - Monitor order status
• **Automatic Stock Updates** - Reduce inventory on order completion

**Current Status:**
The order management module is currently being developed. The system has:
• Database structure ready for orders
• Stock update functionality implemented
• Integration points with customer and item modules

**Available Now:**
• Customer management for order processing
• Item inventory for order fulfillment
• Foundation for order processing workflow

**Coming Soon:**
• Complete order form interface
• Order history and tracking
• Order reports and analytics

Would you like information about customer or inventory management while we complete the order system?`;
    }

    getReportsResponse(message) {
        return `**Reports & Analytics (Planned Features):**

**Upcoming Report Types:**
• **Sales Reports** - Revenue and sales analysis
• **Inventory Reports** - Stock levels and movement
• **Customer Reports** - Customer activity and demographics
• **Order Reports** - Order history and trends

**Analytics Dashboard:**
• Real-time sales metrics
• Low stock alerts
• Top-selling items
• Customer purchase patterns
• Revenue trends

**Export Options:**
• PDF reports for printing
• Excel exports for further analysis
• Scheduled report generation

**Current Data Available:**
• Customer database with complete records
• Item inventory with stock levels
• Foundation for comprehensive reporting

**Implementation Status:**
Reports module is in planning phase. The database structure supports comprehensive reporting once implemented.

Would you like help with current data management features?`;
    }

    getDatabaseResponse(message) {
        return `**Thogakade Database Structure:**

**Current Tables:**
1. **CUSTOMER Table:**
   • CustID (Primary Key)
   • CustTitle, CustName, DOB
   • Salary, CustAddress
   • City, Province, PostalCode

2. **item Table:**
   • ItemCode (Primary Key)
   • Description, PackSize
   • UnitPrice, QtyOnHand

**Database Configuration:**
• **Database:** MySQL
• **Connection:** localhost:3306/thogakade
• **Features:** CRUD operations, connection pooling

**Technical Implementation:**
• Java-based database connectivity
• Prepared statements for security
• Connection management through DbConnection class
• Service layer architecture

**Data Operations:**
• Customer: Add, Delete, View All, Search, Get IDs
• Items: Add, Update, Delete, Search, View All, Stock Updates

**Security Features:**
• Prepared statements prevent SQL injection
• Connection pooling for performance
• Error handling and validation

Need help with specific database operations or connectivity issues?`;
    }

    getHelpResponse() {
        return `**Thogakade Store Management System Help:**

**Main Modules:**
1. **Customer Management** - Add, view, delete customers
2. **Item Management** - Inventory control and stock management
3. **Order Management** - (Coming soon) Order processing
4. **Reports** - (Planned) Analytics and reporting

**How to Use:**
• **Dashboard** - Central hub with module access buttons
• **Forms** - User-friendly interfaces for data entry
• **Tables** - View and select records easily
• **Buttons** - Clear action buttons for all operations

**Getting Started:**
1. Use the dashboard to navigate to different modules
2. Customer Form: Manage customer information
3. Item Form: Handle inventory and stock
4. Each form has Add, Update, Delete, and Search capabilities

**Technical Support:**
• Built with JavaFX and MySQL
• Modern UI with JFoenix components
• Robust database connectivity
• Error handling and validation

**Need Specific Help?**
Ask me about:
• Adding customers or items
• Database operations
• Form usage
• System features

What would you like to learn more about?`;
    }

    getDefaultResponse() {
        const responses = [
            "I'm here to help with your Thogakade Store Management System. You can ask me about customers, inventory, orders, or general system usage.",
            "I can assist you with customer management, item inventory, database operations, and system navigation. What would you like to know?",
            "Feel free to ask about any aspect of the Thogakade system - from adding customers to managing inventory. How can I help?",
            "I'm your Thogakade assistant! I can help with customer operations, inventory management, system features, and more. What's your question?"
        ];
        
        return responses[Math.floor(Math.random() * responses.length)];
    }

    handleQuickAction(action) {
        const quickActions = {
            'customer-help': "How can I help you with customer management? I can guide you through adding new customers, updating customer information, deleting records, or searching for specific customers.",
            'inventory-check': "Let me help you with inventory management. I can explain how to add new items, update stock quantities, search for products, or manage your item database.",
            'order-status': "The order management system is currently in development. However, I can help you prepare by setting up customers and inventory items that will be used for order processing.",
            'reports': "The reporting module is planned for future release. It will include sales analytics, inventory reports, customer insights, and comprehensive business intelligence features."
        };

        const message = quickActions[action] || "I'm here to help with any questions about the Thogakade Store Management System.";
        this.addMessage(message, 'bot');
        this.saveChatHistory();
    }

    showTypingIndicator() {
        this.typingIndicator.classList.add('show');
        this.scrollToBottom();
    }

    hideTypingIndicator() {
        this.typingIndicator.classList.remove('show');
    }

    scrollToBottom() {
        setTimeout(() => {
            this.messagesContainer.scrollTop = this.messagesContainer.scrollHeight;
        }, 100);
    }

    clearChat() {
        // Keep only the initial welcome message
        const welcomeMessage = this.messagesContainer.querySelector('.message');
        this.messagesContainer.innerHTML = '';
        this.messagesContainer.appendChild(welcomeMessage);
        
        // Clear localStorage
        localStorage.removeItem('thogakade_chat_history');
        
        // Show confirmation
        this.addMessage("Chat history cleared! How can I help you today?", 'bot');
    }

    saveChatHistory() {
        const messages = Array.from(this.messagesContainer.querySelectorAll('.message')).map(msg => ({
            content: msg.querySelector('.message-content').innerHTML,
            sender: msg.classList.contains('user-message') ? 'user' : 'bot',
            time: msg.querySelector('.message-time').textContent
        }));
        
        localStorage.setItem('thogakade_chat_history', JSON.stringify(messages));
    }

    loadChatHistory() {
        const history = localStorage.getItem('thogakade_chat_history');
        if (history) {
            const messages = JSON.parse(history);
            // Skip the first message (welcome message) as it's already in HTML
            messages.slice(1).forEach(msg => {
                this.addMessageFromHistory(msg.content, msg.sender, msg.time);
            });
        }
    }

    addMessageFromHistory(content, sender, time) {
        const messageDiv = document.createElement('div');
        messageDiv.className = `message ${sender}-message`;
        
        messageDiv.innerHTML = `
            <div class="message-avatar">
                <i class="fas fa-${sender === 'user' ? 'user' : 'robot'}"></i>
            </div>
            <div class="message-content">
                ${content}
            </div>
            <div class="message-time">${time}</div>
        `;

        this.messagesContainer.appendChild(messageDiv);
    }
}

// Initialize the chatbot when the page loads
document.addEventListener('DOMContentLoaded', () => {
    new ThogakadeChatbot();
});

// Add some additional interactive features
document.addEventListener('DOMContentLoaded', () => {
    // Add smooth scrolling behavior
    document.documentElement.style.scrollBehavior = 'smooth';
    
    // Add keyboard shortcuts
    document.addEventListener('keydown', (e) => {
        // Ctrl/Cmd + K to focus on input
        if ((e.ctrlKey || e.metaKey) && e.key === 'k') {
            e.preventDefault();
            document.getElementById('messageInput').focus();
        }
        
        // Escape to clear input
        if (e.key === 'Escape') {
            document.getElementById('messageInput').value = '';
            document.getElementById('sendBtn').disabled = true;
        }
    });
    
    // Add visual feedback for button interactions
    document.querySelectorAll('button').forEach(button => {
        button.addEventListener('click', function() {
            this.style.transform = 'scale(0.95)';
            setTimeout(() => {
                this.style.transform = '';
            }, 150);
        });
    });
});