import Navbar from "@/components/Navbar"

export default function DashboardLayout({
                                            children,
                                        }: {
    children: React.ReactNode
}) {
    return (
        <div className="min-h-screen bg-gradient-to-br from-[#0A2540] via-[#0F3D6B] to-[#0A2540]">
            {/* Top Navbar - now visible on all dashboard pages */}
            <Navbar />

            {/* Main content area */}
            <main className="relative z-10 pt-24 pb-16 px-6 md:px-12 max-w-7xl mx-auto">
                {children}
            </main>
        </div>
    )
}