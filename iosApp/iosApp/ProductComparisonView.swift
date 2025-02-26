import SwiftUI
import Kingfisher

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
            if let imageUrl = product.imageUrl {
                KFImage(URL(string: imageUrl))
                    .resizable()
                    .scaledToFit()
                    .frame(height: 100)
                    .cornerRadius(8)
            } else {
                Rectangle()
                    .fill(Color.gray.opacity(0.2))
                    .frame(height: 100)
                    .cornerRadius(8)
            }
            Text(product.name)
                .font(.headline)
            Text("Price: \(product.price)")
        }
        .padding()
        .background(Color.white)
        .cornerRadius(12)
        .shadow(radius: 4)
    }
}

struct Product {
    var name: String
    var price: String
    var imageUrl: String?
}
