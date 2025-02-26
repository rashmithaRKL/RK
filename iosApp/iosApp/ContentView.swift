import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        NavigationView {
            VStack {
                Text("Welcome to the Shopping App")
                    .font(.largeTitle)
                    .padding()

                NavigationLink(destination: ProductComparisonView(product1: Product(name: "Product 1", price: "$10"), product2: Product(name: "Product 2", price: "$20"))) {
                    Text("Go to Product Comparison")
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                }
            }
        }
    }
}
