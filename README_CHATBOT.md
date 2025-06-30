# Thogakade AI Chatbot

An intelligent AI assistant for the Thogakade Store Management System, providing instant help and guidance for users.

## 🤖 Features

### Core Capabilities
- **Intelligent Responses** - Context-aware answers about system operations
- **Multi-topic Support** - Customer management, inventory, orders, and system help
- **Interactive UI** - Modern, responsive chat interface
- **Quick Actions** - Pre-defined buttons for common queries
- **Chat History** - Persistent conversation storage
- **Real-time Typing** - Visual feedback during response generation

### Knowledge Areas
- **Customer Management** - Adding, deleting, searching customers
- **Inventory Control** - Item management, stock updates, search functionality
- **Order Processing** - Status updates and preparation guidance
- **Database Operations** - MySQL connectivity and table structure
- **System Navigation** - Dashboard usage and form operations

## 🚀 Quick Start

### Prerequisites
- Node.js (v16 or higher)
- npm or yarn package manager

### Installation

1. **Install dependencies:**
```bash
npm install
```

2. **Start development server:**
```bash
npm run dev
```

3. **Start the AI backend (optional):**
```bash
node src/server/chatbot-server.js
```

The chatbot will be available at `http://localhost:3000`

## 🏗️ Architecture

### Frontend Components
- **Chat Interface** - Modern, responsive UI with animations
- **Message System** - Real-time message handling and display
- **Quick Actions** - Predefined help categories
- **Local Storage** - Chat history persistence

### Backend API (Optional)
- **Express Server** - RESTful API for advanced AI features
- **Session Management** - Context-aware conversations
- **Knowledge Base** - Structured information about Thogakade system

### File Structure
```
src/
├── styles/
│   └── main.css          # Complete UI styling
├── scripts/
│   └── main.js           # Chat functionality and AI logic
└── server/
    └── chatbot-server.js # Optional backend API
```

## 💬 Usage Guide

### Basic Interaction
1. Type your question in the input field
2. Press Enter or click the send button
3. Receive instant, contextual responses
4. Use quick action buttons for common queries

### Supported Query Types

**Customer Management:**
- "How do I add a customer?"
- "Delete customer process"
- "Customer search options"

**Inventory Management:**
- "Add new items to inventory"
- "Update stock quantities"
- "Search for products"

**System Help:**
- "How to navigate the dashboard"
- "Database connection info"
- "System features overview"

### Quick Actions
- **Customer Help** - Customer management guidance
- **Inventory Check** - Inventory operations help
- **Order Status** - Order system information
- **Reports** - Analytics and reporting info

## 🎨 Customization

### Styling
Modify `src/styles/main.css` to customize:
- Color schemes and themes
- Typography and spacing
- Animation effects
- Responsive breakpoints

### AI Responses
Update `src/scripts/main.js` to modify:
- Response templates
- Knowledge base content
- Quick action behaviors
- Message formatting

### Backend Integration
Enhance `src/server/chatbot-server.js` for:
- External AI API integration
- Database connectivity
- Advanced session management
- Analytics and logging

## 🔧 Configuration

### Environment Variables
Create a `.env` file based on `.env.example`:

```env
PORT=3001
OPENAI_API_KEY=your_key_here
DB_HOST=localhost
DB_NAME=thogakade
```

### Vite Configuration
Modify `vite.config.js` for:
- Development server settings
- Build optimization
- Proxy configuration
- Asset handling

## 🚀 Deployment

### Build for Production
```bash
npm run build
```

### Preview Production Build
```bash
npm run preview
```

### Deploy Options
- **Static Hosting** - Netlify, Vercel, GitHub Pages
- **Server Deployment** - Heroku, DigitalOcean, AWS
- **Local Network** - Internal company servers

## 🔌 Integration with JavaFX

### WebView Integration
Add to your JavaFX application:

```java
WebView webView = new WebView();
WebEngine webEngine = webView.getEngine();
webEngine.load("http://localhost:3000");
```

### Embedded Browser
- Load the chatbot in a WebView component
- Integrate with existing JavaFX forms
- Share data between JavaFX and web components

## 📊 Analytics & Monitoring

### Built-in Metrics
- Chat session tracking
- Message count statistics
- Popular query analysis
- Response time monitoring

### Health Monitoring
Access `/api/health` for system status
Access `/api/stats` for usage statistics

## 🛠️ Development

### Adding New Features
1. **New Knowledge Areas** - Extend the knowledge base in `main.js`
2. **UI Components** - Add new interface elements
3. **API Endpoints** - Create additional backend routes
4. **Integrations** - Connect with external services

### Testing
- Manual testing through the chat interface
- API testing with tools like Postman
- Browser developer tools for debugging

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## 📝 License

This project is part of the Thogakade Store Management System and follows the same licensing terms.

## 🆘 Support

For technical support or questions:
- Check the built-in help system
- Review the knowledge base responses
- Contact the development team
- Submit issues through the project repository

---

**Built with modern web technologies for seamless integration with the Thogakade Store Management System.**