import express from 'express';
import cors from 'cors';
import dotenv from 'dotenv';

dotenv.config();

const app = express();
const PORT = process.env.PORT || 3001;

// Middleware
app.use(cors());
app.use(express.json());
app.use(express.static('dist'));

// In-memory storage for chat sessions (in production, use a database)
const chatSessions = new Map();

// Enhanced AI response system
class ThogakadeAI {
    constructor() {
        this.knowledgeBase = {
            customers: {
                add: "To add a customer, fill the form with ID, title, name, DOB, salary, address, city, province, and postal code, then click Add.",
                delete: "Select a customer from the table and click Delete. This action cannot be undone.",
                search: "Click on any customer row to view their details. The system loads all customers for browsing.",
                update: "Customer updates are planned for future releases. Currently, you can add and delete customers."
            },
            items: {
                add: "Enter item code, description, pack size, unit price, and quantity, then click ADD.",
                update: "Select an item from the table, modify the details, and click UPDATE.",
                delete: "Select an item and click DELETE to remove it from inventory.",
                search: "Enter the item code and click SEARCH, or browse all items in the table."
            },
            orders: {
                status: "Order management is in development. Customer and item modules are ready for order processing.",
                create: "Order creation will be available soon. Ensure customers and items are set up first."
            },
            system: {
                database: "Uses MySQL database with CUSTOMER and item tables. Connection: localhost:3306/thogakade",
                navigation: "Use the dashboard buttons to access Customer Form, Item Form, and other modules.",
                features: "Current features include customer management, inventory control, and database operations."
            }
        };
    }

    generateResponse(message, sessionId) {
        const lowerMessage = message.toLowerCase();
        
        // Context-aware responses based on conversation history
        const session = chatSessions.get(sessionId) || { context: [], messageCount: 0 };
        session.messageCount++;
        
        let response = this.getContextualResponse(lowerMessage, session.context);
        
        // Update session context
        session.context.push({ message: lowerMessage, response: response.toLowerCase() });
        if (session.context.length > 5) {
            session.context.shift(); // Keep only last 5 exchanges
        }
        
        chatSessions.set(sessionId, session);
        
        return {
            response,
            suggestions: this.getSuggestions(lowerMessage),
            sessionId
        };
    }

    getContextualResponse(message, context) {
        // Check for greetings
        if (this.isGreeting(message)) {
            return this.getGreetingResponse();
        }

        // Check for specific topics
        if (message.includes('customer')) {
            return this.getCustomerResponse(message);
        }
        
        if (message.includes('item') || message.includes('inventory')) {
            return this.getItemResponse(message);
        }
        
        if (message.includes('order')) {
            return this.getOrderResponse(message);
        }
        
        if (message.includes('database') || message.includes('mysql')) {
            return this.getDatabaseResponse(message);
        }
        
        if (message.includes('help') || message.includes('how')) {
            return this.getHelpResponse();
        }

        // Contextual responses based on conversation history
        if (context.length > 0) {
            const lastContext = context[context.length - 1];
            if (lastContext.response.includes('customer') && !message.includes('customer')) {
                return "Are you still asking about customer management, or would you like help with something else?";
            }
        }

        return this.getDefaultResponse();
    }

    isGreeting(message) {
        const greetings = ['hello', 'hi', 'hey', 'good morning', 'good afternoon', 'good evening'];
        return greetings.some(greeting => message.includes(greeting));
    }

    getGreetingResponse() {
        const responses = [
            "Hello! Welcome to Thogakade Store Management System. I'm your AI assistant, ready to help with customers, inventory, orders, and system navigation.",
            "Hi there! I'm here to assist you with the Thogakade system. What would you like to know about?",
            "Greetings! I can help you with customer management, inventory control, and system operations. How can I assist you today?"
        ];
        return responses[Math.floor(Math.random() * responses.length)];
    }

    getCustomerResponse(message) {
        if (message.includes('add') || message.includes('create')) {
            return this.knowledgeBase.customers.add + "\n\nRequired fields: Customer ID (unique), Title, Name, Date of Birth, Salary, Address, City, Province, Postal Code.";
        }
        if (message.includes('delete')) {
            return this.knowledgeBase.customers.delete + "\n\nWarning: Make sure the customer doesn't have existing orders before deletion.";
        }
        if (message.includes('search') || message.includes('find')) {
            return this.knowledgeBase.customers.search + "\n\nTip: Use the reload button to refresh the customer table with latest data.";
        }
        return "I can help you with customer operations: adding new customers, deleting existing ones, or searching through customer records. What specific customer task do you need help with?";
    }

    getItemResponse(message) {
        if (message.includes('add') || message.includes('create')) {
            return this.knowledgeBase.items.add + "\n\nTip: Use descriptive item codes and detailed descriptions for easy identification.";
        }
        if (message.includes('update') || message.includes('edit')) {
            return this.knowledgeBase.items.update + "\n\nNote: Item codes cannot be changed after creation. All other fields are editable.";
        }
        if (message.includes('delete')) {
            return this.knowledgeBase.items.delete + "\n\nWarning: Ensure the item isn't part of any pending orders before deletion.";
        }
        if (message.includes('search')) {
            return this.knowledgeBase.items.search + "\n\nThe table shows all items with their current stock levels.";
        }
        return "I can assist with inventory management: adding items, updating stock, deleting products, or searching inventory. What inventory task would you like help with?";
    }

    getOrderResponse(message) {
        return this.knowledgeBase.orders.status + "\n\nCurrent capabilities: Customer management and inventory control are ready. Order processing interface is coming soon.";
    }

    getDatabaseResponse(message) {
        return this.knowledgeBase.system.database + "\n\nThe system uses prepared statements for security and connection pooling for performance. Both CUSTOMER and item tables support full CRUD operations.";
    }

    getHelpResponse() {
        return `**Thogakade System Overview:**

**Available Modules:**
• Customer Management - Add, view, delete customers
• Item Management - Full inventory control
• Order Management - Coming soon
• Reports & Analytics - Planned feature

**Navigation:**
Use the dashboard buttons to access different modules. Each form provides intuitive interfaces for data management.

**Need Specific Help?**
Ask me about: adding customers, managing inventory, database operations, or system features.`;
    }

    getDefaultResponse() {
        const responses = [
            "I'm here to help with your Thogakade Store Management System. Could you be more specific about what you need assistance with?",
            "I can help with customers, inventory, system navigation, or database questions. What would you like to know?",
            "Feel free to ask about any Thogakade feature - customer management, item inventory, or system operations. How can I assist?",
            "I'm your Thogakade assistant! Ask me about adding customers, managing inventory, or using system features."
        ];
        return responses[Math.floor(Math.random() * responses.length)];
    }

    getSuggestions(message) {
        if (message.includes('customer')) {
            return ['How to add customers?', 'Delete customer records', 'Customer search options'];
        }
        if (message.includes('item') || message.includes('inventory')) {
            return ['Add new items', 'Update stock quantities', 'Search inventory'];
        }
        if (message.includes('order')) {
            return ['Order system status', 'Customer setup for orders', 'Inventory preparation'];
        }
        return ['Customer management', 'Inventory control', 'System navigation', 'Database operations'];
    }
}

const ai = new ThogakadeAI();

// API Routes
app.post('/api/chat', (req, res) => {
    try {
        const { message, sessionId = 'default' } = req.body;
        
        if (!message || typeof message !== 'string') {
            return res.status(400).json({ error: 'Message is required and must be a string' });
        }

        const result = ai.generateResponse(message, sessionId);
        
        res.json({
            success: true,
            ...result,
            timestamp: new Date().toISOString()
        });
    } catch (error) {
        console.error('Chat API error:', error);
        res.status(500).json({ 
            error: 'Internal server error',
            message: 'Sorry, I encountered an error. Please try again.'
        });
    }
});

// Health check endpoint
app.get('/api/health', (req, res) => {
    res.json({ 
        status: 'healthy', 
        timestamp: new Date().toISOString(),
        activeSessions: chatSessions.size
    });
});

// Get chat statistics
app.get('/api/stats', (req, res) => {
    const stats = {
        totalSessions: chatSessions.size,
        totalMessages: Array.from(chatSessions.values()).reduce((sum, session) => sum + session.messageCount, 0),
        averageMessagesPerSession: chatSessions.size > 0 ? 
            Array.from(chatSessions.values()).reduce((sum, session) => sum + session.messageCount, 0) / chatSessions.size : 0
    };
    
    res.json(stats);
});

// Serve the main application
app.get('/', (req, res) => {
    res.sendFile('index.html', { root: 'dist' });
});

// Error handling middleware
app.use((err, req, res, next) => {
    console.error(err.stack);
    res.status(500).json({ error: 'Something went wrong!' });
});

// Start server
app.listen(PORT, () => {
    console.log(`🤖 Thogakade AI Chatbot Server running on port ${PORT}`);
    console.log(`📱 Access the chatbot at: http://localhost:${PORT}`);
});

export default app;