import Image from "next/image";
import Link from "next/link";

export default function ProductCard({ category, setSelectedProduct, setModalIsOpen }) {


    return (
        <div className="grid lg:grid-cols-2 lg:gap-2 border border-gray-700 px-4 py-4 rounded-xl">
            <div className="font-bold text-xl sm:text-2xl  mb-4 lg:col-span-2">
                {category.name}
            </div>
            {category.products.map((product, index) => (
                <button key={index} onClick={() => {
                    setSelectedProduct(product)
                    setModalIsOpen(true)
                }}>
                    <div className="flex my-3 pb-3 gap-5 border-b border-gray-800 items-center">
                        <Image
                            src="https://res.cloudinary.com/dusgvgbgf/image/upload/v1739903483/burguer.jpg"
                            width={1000}
                            height={0}
                            alt="burguer"
                            className="w-32 h-28 object-cover rounded-lg"
                        />

                        <div className="flex flex-col items-start py-3">
                            <p className="text-lg sm:text-2xl text-left">
                                {product.name}
                            </p>
                            <p className="text-gray-500 mt-3 text-left">
                                {product.description}
                            </p>
                            <p className="mt-3 text-xl">
                                R$ {product.basePrice.toFixed(2).replace('.', ',')}
                            </p>
                        </div>
                    </div>
                </button>
            ))}
        </div>
    )
}