import SwiftUI

struct ProductComparisonView: View {
    var product1: Product
    var product2: Product

    var body: some View {
        VStack {
            Text("Compare Products")
                .font(.largeTitle)
                .padding()

            HStack {
                ProductCard(product: product1)
                Spacer()
                ProductCard(product: product2)
            }
            .padding()

            Button(action: {
                // Handle Swap Action
            }) {
                Text("Swap")
                    .padding()
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }
        }
    }
}

struct ProductCard: View {
    var product: Product

    var body: some View {
        VStack {
            Text(product.name)
                .font(.headline)
            // Load product image here
            Text("Price: \(product.price)")
        }
        .padding()
        .border(Color.gray, width: 1)
    }
}

struct Product {
    var name: String
    var price: String
}
