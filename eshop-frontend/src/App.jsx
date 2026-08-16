import { useState, useEffect } from 'react'
import './App.css'

function App() {
  const [products, setProducts] = useState([])
  const [loading, setLoading] = useState(true)
  const [cart, setCart] = useState([])

  const fetchProducts = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/products')
      const data = await response.json()
      setProducts(data)
    } catch (error) {
      console.error('Chyba při komunikaci s Java backendem:', error)
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    fetchProducts()
  }, [])

  const addToCart = (product) => {
    setCart((prevCart) => {
      const existingItem = prevCart.find((item) => item.id === product.id)
      if (existingItem) {
        return prevCart.map((item) =>
          item.id === product.id ? { ...item, quantity: item.quantity + 1 } : item
        )
      }
      return [...prevCart, { ...product, quantity: 1 }]
    })
  }

  const removeFromCart = (productId) => {
    setCart((prevCart) => {
      const existingItem = prevCart.find((item) => item.id === productId)
      if (existingItem.quantity > 1) {
        return prevCart.map((item) =>
          item.id === productId ? { ...item, quantity: item.quantity - 1 } : item
        )
      } else {
        return prevCart.filter((item) => item.id !== productId)
      }
    })
  }

  const clearProductFromCart = (productId) => {
    setCart((prevCart) => prevCart.filter((item) => item.id !== productId))
  }

  const checkout = async () => {
    if (cart.length === 0) return
    const orderData = {
      items: cart.map(item => ({
        productId: item.id,
        productName: item.name,
        price: item.price,
        quantity: item.quantity
      }))
    }
    try {
      const response = await fetch('http://localhost:8080/api/orders', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(orderData)
      })
      if (response.ok) {
        const savedOrder = await response.json()
        alert(`Objednávka úspěšně vytvořena! ID v MySQL databázi: ${savedOrder.id}`)
        setCart([])
      } else {
        alert('Došlo k chybě při odesílání objednávky.')
      }
    } catch (error) {
      console.error('Chyba sítě:', error)
      alert('Nelze se spojit s backendem.')
    }
  }

  const totalCartPrice = cart.reduce((sum, item) => sum + item.price * item.quantity, 0)
  const totalItemsCount = cart.reduce((sum, item) => sum + item.quantity, 0)

  return (
    <div className="eshop-app">
      {/* NOVÁ HORNÍ NAVIGAČNÍ LIŠTA */}
      <nav className="navbar">
        <div className="navbar-container">
          <div className="navbar-logo">⚡ AnalytickýEshop</div>
          <ul className="navbar-menu">
            <li><a href="#products" className="active">Produkty</a></li>
            <li><a href="#about">O nás</a></li>
            <li><a href="#contact">Kontakt</a></li>
          </ul>
          <div className="navbar-cart-badge">
            🛒 Košík ({totalItemsCount})
          </div>
        </div>
      </nav>

      {/* REKLAMNÍ BANNER (HERO SEKCE) */}
      <header className="hero-banner">
        <h1>Moderní e-shop nové generace</h1>
        <p>Full-stack aplikace postavená na Java Spring Boot, MySQL v Dockeru a React frontendu.</p>
      </header>

      {/* HLAVNÍ OBSAH */}
      <main className="main-content" id="products">
        <div className="content-layout">

          {/* LEVÝ PANEL: PRODUKTY */}
          <section className="products-section">
            <h2>Nabídka produktů</h2>
            {loading ? (
              <div className="loading">Načítám čerstvá data z MySQL databáze...</div>
            ) : (
              <div className="products-grid">
                {products.map((product) => (
                  <div key={product.id} className="product-card">
                    <div className="product-icon">📦</div>
                    <h3>{product.name}</h3>
                    <p className="description">{product.description}</p>
                    <div className="card-footer">
                      <span className="price">{product.price.toLocaleString()} Kč</span>
                      <button className="buy-btn" onClick={() => addToCart(product)}>Do košíku</button>
                    </div>
                  </div>
                ))}
              </div>
            )}
          </section>

          {/* PRAVÝ PANEL: KOŠÍK */}
          <aside className="cart-sidebar">
            <div className="sticky-sidebar">
              <h2>Nákupní košík</h2>
              <div className="cart-box">
                {cart.length === 0 ? (
                  <p className="empty-cart-msg">Košík je zatím prázdný. Přidejte si nějaké produkty z naší nabídky.</p>
                ) : (
                  <>
                    <ul className="cart-list">
                      {cart.map((item) => (
                        <li key={item.id} className="cart-item-row">
                          <div className="cart-item-info">
                            <span className="cart-item-name">{item.name}</span>
                            <span className="cart-item-qty">{item.quantity}x</span>
                          </div>
                          <div className="cart-item-actions">
                            <span className="cart-item-price">{(item.price * item.quantity).toLocaleString()} Kč</span>
                            <button className="remove-btn" onClick={() => removeFromCart(item.id)} title="Odebrat 1 ks">-</button>
                            <button className="clear-btn" onClick={() => clearProductFromCart(item.id)} title="Smazat celou řadu">🗑️</button>
                          </div>
                        </li>
                      ))}
                    </ul>
                    <div className="cart-summary-total">
                      <span>Celková cena:</span>
                      <strong>{totalCartPrice.toLocaleString()} Kč</strong>
                    </div>
                    <button className="checkout-btn" onClick={checkout}>Odeslat závaznou objednávku</button>
                  </>
                )}
              </div>
            </div>
          </aside>

        </div>
      </main>

      {/* NOVÁ PATIČKA WEB STRÁNKY */}
      <footer className="eshop-footer" id="contact">
        <p>© 2026 AnalytickýEshop. Všechna práva vyhrazena. Kontakt: info@analytickyeshop.cz | Tel: +420 123 456 789</p>
      </footer>
    </div>
  )
}

export default App
