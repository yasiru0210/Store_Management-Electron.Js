# Thogakade Store Management System

A modern, cross-platform desktop store management application built with Electron.js, featuring a beautiful user interface and comprehensive inventory management capabilities.

![Thogakade Dashboard](https://images.pexels.com/photos/264636/pexels-photo-264636.jpeg?auto=compress&cs=tinysrgb&w=800)

## 🚀 Features

### Core Modules
- **Customer Management** - Add, view, update, and delete customer records
- **Item Management** - Complete inventory control with search functionality
- **Order Management** - *(Coming Soon)*
- **Reports & Analytics** - *(Coming Soon)*

### Technical Highlights
- **Modern UI/UX** - Beautiful gradients, animations, and responsive design
- **Real-time Notifications** - Toast notifications for user feedback
- **Database Integration** - MySQL database with async operations
- **Cross-platform** - Runs on Windows, macOS, and Linux
- **Responsive Design** - Adapts to different screen sizes

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

- **Node.js** (v16 or higher) - [Download here](https://nodejs.org/)
- **MySQL Server** - [Download here](https://dev.mysql.com/downloads/mysql/)
- **Git** (optional) - For cloning the repository

## 🗄️ Database Setup

1. **Create the MySQL database:**
```sql
CREATE DATABASE thogakade;
USE thogakade;
```

2. **Create the required tables:**

```sql
-- Customer table
CREATE TABLE CUSTOMER (
    CustID VARCHAR(10) PRIMARY KEY,
    CustTitle VARCHAR(10) NOT NULL,
    CustName VARCHAR(100) NOT NULL,
    DOB DATE NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    CustAddress VARCHAR(200) NOT NULL,
    City VARCHAR(50) NOT NULL,
    Province VARCHAR(50) NOT NULL,
    PostalCode VARCHAR(10) NOT NULL
);

-- Item table
CREATE TABLE item (
    ItemCode VARCHAR(10) PRIMARY KEY,
    Description VARCHAR(200) NOT NULL,
    PackSize VARCHAR(50) NOT NULL,
    UnitPrice DECIMAL(10,2) NOT NULL,
    QtyOnHand INT NOT NULL
);
```

3. **Update database credentials** in `src/database/db.js`:
```javascript
this.connection = await mysql.createConnection({
  host: 'localhost',
  user: 'your_username',     // Update this
  password: 'your_password', // Update this
  database: 'thogakade'
});
```

## 🛠️ Installation & Setup

1. **Clone or download the project:**
```bash
git clone <repository-url>
cd thogakade-electron
```

2. **Install dependencies:**
```bash
npm install
```

3. **Start the application:**
```bash
npm start
```

For development mode with DevTools:
```bash
npm run dev
```

## 📦 Building for Distribution

To create distributable packages:

```bash
# Build for current platform
npm run build

# Create distribution packages
npm run dist
```

This will create installers in the `dist/` folder for your operating system.

## 🎯 Usage Guide

### Dashboard
- Launch the application to see the main dashboard
- Click on any module button to navigate to that section
- Beautiful animations and hover effects enhance the user experience

### Customer Management
- **Add Customer**: Fill the form and click "Add Customer"
- **Select Customer**: Click on any row in the table to select
- **Delete Customer**: Select a customer and click "Delete"
- **Clear Form**: Reset all form fields

### Item Management
- **Add Item**: Enter item details and click "Add Item"
- **Update Item**: Select an item, modify details, and click "Update"
- **Delete Item**: Select an item and click "Delete"
- **Search Item**: Enter item code and click "Search"

## 🏗️ Project Structure

```
thogakade-electron/
├── src/
│   ├── main.js                 # Main Electron process
│   ├── database/
│   │   └── db.js              # Database connection & operations
│   ├── renderer/
│   │   ├── dashboard.html     # Main dashboard
│   │   ├── customer.html      # Customer management
│   │   ├── item.html          # Item management
│   │   ├── scripts/
│   │   │   ├── dashboard.js   # Dashboard functionality
│   │   │   ├── customer.js    # Customer operations
│   │   │   └── item.js        # Item operations
│   │   └── styles/
│   │       ├── dashboard.css  # Dashboard styling
│   │       └── forms.css      # Form styling
│   └── assets/
│       └── store-hero.jpg     # Hero image
├── package.json               # Project configuration
└── README.md                 # This file
```

## 🔧 Configuration

### Database Configuration
Update the database connection settings in `src/database/db.js`:
- Host
- Username
- Password
- Database name

### Application Settings
Modify `package.json` for:
- Application name
- Version
- Author information
- Build configuration

## 🚨 Troubleshooting

### Common Issues

**Database Connection Failed:**
- Ensure MySQL server is running
- Verify database credentials
- Check if the database and tables exist

**Application Won't Start:**
- Run `npm install` to ensure all dependencies are installed
- Check Node.js version (should be v16+)
- Look for error messages in the console

**Build Issues:**
- Clear node_modules: `rm -rf node_modules && npm install`
- Update Electron: `npm update electron`

### Error Messages
- **"Cannot connect to database"** - Check MySQL service and credentials
- **"Table doesn't exist"** - Run the database setup SQL commands
- **"Permission denied"** - Check file permissions and run as administrator if needed

## 🛣️ Roadmap

### Upcoming Features
- [ ] Order Management System
- [ ] Reports and Analytics Dashboard
- [ ] Data Export (PDF, Excel)
- [ ] Backup and Restore
- [ ] User Authentication
- [ ] Multi-language Support
- [ ] Dark/Light Theme Toggle

### Technical Improvements
- [ ] Unit Testing
- [ ] Database Migration System
- [ ] Logging System
- [ ] Auto-updater
- [ ] Performance Optimization

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)
- Email: your.email@example.com

## 🙏 Acknowledgments

- Built with [Electron.js](https://www.electronjs.org/)
- Database powered by [MySQL](https://www.mysql.com/)
- Icons and images from [Pexels](https://www.pexels.com/)
- UI inspiration from modern web design trends

## 📞 Support

If you encounter any issues or have questions:
1. Check the troubleshooting section above
2. Search existing issues on GitHub
3. Create a new issue with detailed information
4. Contact the development team

---

**Made with ❤️ using Electron.js**